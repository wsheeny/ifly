package com.wyc.amor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import com.wyc.amor.config.redis.RedisService;
import com.wyc.amor.mapper.BmsTagMapper;
import com.wyc.amor.mapper.BmsTopicMapper;
import com.wyc.amor.mapper.UserMapper;
import com.wyc.amor.model.dto.CreateTopicDTO;
import com.wyc.amor.model.entity.BmsColumn;
import com.wyc.amor.model.entity.BmsPost;
import com.wyc.amor.model.entity.BmsTag;
import com.wyc.amor.model.entity.BmsTopicTag;
import com.wyc.amor.model.entity.UmsUser;
import com.wyc.amor.model.vo.PostVO;
import com.wyc.amor.model.vo.ProfileVO;
import com.wyc.amor.service.IBmsPostService;
import com.wyc.amor.service.IBmsTagService;
import com.wyc.amor.service.IUmsUserService;
import com.wyc.amor.service.IBmsTopicTagService;
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
    private UserMapper userMapper;

    @Resource
    private BmsTagMapper bmsTagMapper;

    @Autowired
    @Lazy
    private IBmsTagService IBmsTagService;

    @Autowired
    private IBmsTopicTagService IBmsTopicTagService;

    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询话题的标签
        iPage.getRecords().forEach(topic -> {
            List<BmsTopicTag> topicTags = IBmsTopicTagService.selectByTopicId(topic.getId());
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
        for (BmsTopicTag articleTag : IBmsTopicTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<BmsTag> tags = IBmsTagService.listByIds(set);
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
        userMapper.updateById(user.setScore(newScore));

        // 标签
        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<BmsTag> tags = IBmsTagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            IBmsTopicTagService.createTopicTag(topic.getId(), tags);
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
