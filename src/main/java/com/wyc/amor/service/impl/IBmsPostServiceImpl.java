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
import com.wyc.amor.model.entity.TbColumn;
import com.wyc.amor.model.entity.TbPost;
import com.wyc.amor.model.entity.TbTag;
import com.wyc.amor.model.entity.TbTopicTag;
import com.wyc.amor.model.entity.ums.UmsUser;
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
public class IBmsPostServiceImpl extends ServiceImpl<BmsTopicMapper, TbPost> implements IBmsPostService {

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
            List<TbTopicTag> topicTags = IBmsTopicTagService.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(TbTopicTag::getTagId).collect(Collectors.toList());
                List<TbTag> tags = bmsTagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
        return iPage;
    }

    @Override
    public Map<String, Object> viewTopic(String id) {
        Map<String, Object> map = new HashMap<>(16);
        TbPost topic = this.baseMapper.selectById(id);
        Assert.notNull(topic, "当前话题不存在,或已被作者删除");
        // 查询话题详情
        topic.setView(topic.getView() + 1);
        this.baseMapper.updateById(topic);
        // emoji转码
        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        map.put("topic", topic);
        // 标签
        QueryWrapper<TbTopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TbTopicTag::getTopicId, topic.getId());
        Set<String> set = new HashSet<>();
        for (TbTopicTag articleTag : IBmsTopicTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<TbTag> tags = IBmsTagService.listByIds(set);
        map.put("tags", tags);

        // 作者

        ProfileVO user = IUmsUserService.getUserProfile(topic.getUserId());
        map.put("user", user);

        return map;
    }

    @Override
    public List<TbPost> selectAuthorOtherTopic(String userId, String topicId) {
        List<TbPost> topics = (List<TbPost>) redisService.get("otherTopics");

        if (ObjectUtils.isEmpty(topics)) {
            QueryWrapper<TbPost> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(TbPost::getUserId, userId).orderByDesc(TbPost::getCreateTime);
            if (topicId != null) {
                wrapper.lambda().ne(TbPost::getId, topicId);
            }
            wrapper.last("limit " + 10);
            topics = this.baseMapper.selectList(wrapper);
            // 缓存
            redisService.set("otherTopics", topics, 60 * 60);
        }
        return topics;
    }

    @Override
    public Page<TbPost> selectTopicsByUserId(String userId, Page<TbPost> page) {
        QueryWrapper<TbPost> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TbPost::getUserId, userId);
        Page<TbPost> topicPage = this.baseMapper.selectPage(page, wrapper);


        return topicPage;
    }

    @Override
    public List<TbPost> getRecommend(String id) {
        return this.baseMapper.selectRecommend(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TbPost create(CreateTopicDTO dto, UmsUser user) {
        TbPost topic1 = this.baseMapper.selectOne(new LambdaQueryWrapper<TbPost>().eq(TbPost::getTitle, dto.getTitle()));
        Assert.isNull(topic1, "话题已存在，请修改");

        // 封装
        TbPost topic = TbPost.builder()
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
            List<TbTag> tags = IBmsTagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            IBmsTopicTagService.createTopicTag(topic.getId(), tags);
        }
        // TODO: 2020/12/7 es索引
        redisService.del("getTopicListAndPage");
        return topic;
    }

    @Override
    public Page<PostVO> selectByColumn(Page<PostVO> page, TbColumn column) {
        return this.baseMapper.selectByColumn(page, column);
    }

    @Override
    public Page<PostVO> searchByKey(String keyword, Page<PostVO> page) {
        return this.baseMapper.searchByKey(page, keyword);
    }

}
