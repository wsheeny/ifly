package com.knox.aurora.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import com.knox.aurora.config.redis.RedisService;
import com.knox.aurora.mapper.BmsTagMapper;
import com.knox.aurora.mapper.BmsTopicMapper;
import com.knox.aurora.mapper.UmsUserMapper;
import com.knox.aurora.model.dto.CreateTopicDTO;
import com.knox.aurora.model.entity.BmsColumn;
import com.knox.aurora.model.entity.BmsPost;
import com.knox.aurora.model.entity.BmsTag;
import com.knox.aurora.model.entity.BmsTopicTag;
import com.knox.aurora.model.entity.UmsUser;
import com.knox.aurora.model.vo.PostVO;
import com.knox.aurora.model.vo.ProfileVO;
import com.knox.aurora.service.IBmsPostService;
import com.knox.aurora.service.IBmsTagService;
import com.knox.aurora.service.IUmsUserService;
import com.knox.aurora.service.IBmsTopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 议题，话题实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class IBmsPostServiceImpl extends ServiceImpl<BmsTopicMapper, BmsPost> implements IBmsPostService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IUmsUserService IUmsUserService;

    @Resource
    private UmsUserMapper umsUserMapper;

    @Resource
    private BmsTagMapper bmsTagMapper;

    @Autowired
    @Lazy
    private IBmsTagService tagService;

    @Autowired
    private IBmsTopicTagService topicTagService;

    @Override
    public Page<PostVO> getList(Page<PostVO> page) {
        // 查询话题
        // Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        Page<PostVO> iPage = this.baseMapper.selectAll(page);
        // 查询话题的标签
        iPage.getRecords().forEach(topic -> {
            List<BmsTopicTag> topicTags = topicTagService.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                List<BmsTag> tags = bmsTagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
        return iPage;
    }

    @Override
    public Map<String, Object> viewTopic(String id) {
        Map<String, Object> map = new HashMap<>(16);
        BmsPost topic = this.baseMapper.selectById(id);
        Assert.notNull(topic, "当前话题不存在,或已被作者删除");
        // 查询话题详情
        topic.setView(topic.getView() + 1);
        this.baseMapper.updateById(topic);
        // emoji转码
        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        map.put("topic", topic);
        // 标签
        QueryWrapper<BmsTopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsTopicTag::getTopicId, topic.getId());
        Set<String> set = new HashSet<>();
        for (BmsTopicTag articleTag : topicTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<BmsTag> tags = tagService.listByIds(set);
        map.put("tags", tags);

        // 作者

        ProfileVO user = IUmsUserService.getUserProfile(topic.getUserId());
        map.put("user", user);

        return map;
    }

    @Override
    public List<BmsPost> selectAuthorOtherTopic(String userId, String topicId) {
        List<BmsPost> topics = (List<BmsPost>) redisService.get("otherTopics");

        if (ObjectUtils.isEmpty(topics)) {
            QueryWrapper<BmsPost> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(BmsPost::getUserId, userId).orderByDesc(BmsPost::getCreateTime);
            if (topicId != null) {
                wrapper.lambda().ne(BmsPost::getId, topicId);
            }
            wrapper.last("limit " + 10);
            topics = this.baseMapper.selectList(wrapper);
            // 缓存
            redisService.set("otherTopics", topics, 60 * 60);
        }
        return topics;
    }

    @Override
    public Page<BmsPost> selectTopicsByUserId(String userId, Page<BmsPost> page) {
        QueryWrapper<BmsPost> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsPost::getUserId, userId);
        Page<BmsPost> topicPage = this.baseMapper.selectPage(page, wrapper);


        return topicPage;
    }

    @Override
    public List<BmsPost> getRecommend(String id) {
        return this.baseMapper.selectRecommend(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsPost create(CreateTopicDTO dto, UmsUser user) {
        BmsPost topic1 = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getTitle, dto.getTitle()));
        Assert.isNull(topic1, "话题已存在，请修改");

        // 封装
        BmsPost topic = BmsPost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        this.baseMapper.insert(topic);

        // 用户积分增加
        int newScore = user.getScore() + 1;
        umsUserMapper.updateById(user.setScore(newScore));

        // 标签
        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<BmsTag> tags = tagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            topicTagService.createTopicTag(topic.getId(), tags);
        }
        // TODO: 2020/12/7 es索引
        redisService.del("getTopicListAndPage");
        return topic;
    }

    @Override
    public Page<PostVO> selectByColumn(Page<PostVO> page, BmsColumn column) {
        return this.baseMapper.selectByColumn(page, column);
    }

    @Override
    public Page<PostVO> searchByKey(String keyword, Page<PostVO> page) {
        return this.baseMapper.searchByKey(page, keyword);
    }

}
