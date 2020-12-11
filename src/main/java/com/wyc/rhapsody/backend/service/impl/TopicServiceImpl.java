package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import com.wyc.rhapsody.backend.config.redis.RedisService;
import com.wyc.rhapsody.backend.mapper.TagMapper;
import com.wyc.rhapsody.backend.mapper.TopicMapper;
import com.wyc.rhapsody.backend.mapper.UserMapper;
import com.wyc.rhapsody.backend.model.dto.CreateTopicDTO;
import com.wyc.rhapsody.backend.model.entity.*;
import com.wyc.rhapsody.backend.model.vo.ProfileVO;
import com.wyc.rhapsody.backend.model.vo.TopicVO;
import com.wyc.rhapsody.backend.service.TagService;
import com.wyc.rhapsody.backend.service.TopicService;
import com.wyc.rhapsody.backend.service.TopicTagService;
import com.wyc.rhapsody.backend.service.UserService;
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
public class TopicServiceImpl extends ServiceImpl<TopicMapper, TbTopic> implements TopicService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private TagMapper tagMapper;

    @Autowired
    @Lazy
    private TagService tagService;

    @Autowired
    private TopicTagService topicTagService;

    @Override
    public Page<TopicVO> getList(Page<TopicVO> page, String tab) {
        // 查询话题
        Page<TopicVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询话题的标签
        iPage.getRecords().forEach(topic -> {
            List<TbTopicTag> topicTags = topicTagService.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(TbTopicTag::getTagId).collect(Collectors.toList());
                List<TbTag> tags = tagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
        return iPage;
    }

    @Override
    public Map<String, Object> viewTopic(String id) {
        Map<String, Object> map = new HashMap<>(16);
        TbTopic topic = this.baseMapper.selectById(id);
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
        for (TbTopicTag articleTag : topicTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<TbTag> tags = tagService.listByIds(set);
        map.put("tags", tags);

        // 作者

        ProfileVO user = userService.getUserProfile(topic.getUserId());
        map.put("user", user);

        return map;
    }

    @Override
    public List<TbTopic> selectAuthorOtherTopic(String userId, String topicId) {
        List<TbTopic> topics = (List<TbTopic>) redisService.get("otherTopics");

        if (ObjectUtils.isEmpty(topics)) {
            QueryWrapper<TbTopic> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(TbTopic::getUserId, userId).orderByDesc(TbTopic::getCreateTime);
            if (topicId != null) {
                wrapper.lambda().ne(TbTopic::getId, topicId);
            }
            wrapper.last("limit " + 10);
            topics = this.baseMapper.selectList(wrapper);
            // 缓存
            redisService.set("otherTopics", topics, 60 * 60);
        }
        return topics;
    }

    @Override
    public Page<TbTopic> selectTopicsByUserId(String userId, Page<TbTopic> page) {
        QueryWrapper<TbTopic> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TbTopic::getUserId, userId);
        Page<TbTopic> topicPage = this.baseMapper.selectPage(page, wrapper);


        return topicPage;
    }

    @Override
    public List<TbTopic> getRecommend(String id) {
        return this.baseMapper.selectRecommend(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TbTopic create(CreateTopicDTO dto, TbUser user) {
        TbTopic topic1 = this.baseMapper.selectOne(new LambdaQueryWrapper<TbTopic>().eq(TbTopic::getTitle, dto.getTitle()));
        Assert.isNull(topic1, "话题已存在，请修改");

        // 封装
        TbTopic topic = TbTopic.builder()
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
            List<TbTag> tags = tagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            topicTagService.createTopicTag(topic.getId(), tags);
        }
        // TODO: 2020/12/7 es索引
        redisService.del("getTopicListAndPage");
        return topic;
    }

    @Override
    public Page<TopicVO> selectByColumn(Page<TopicVO> page, TbColumn column) {
        return this.baseMapper.selectByColumn(page, column);
    }

    @Override
    public Page<TopicVO> searchByKey(String keyword, Page<TopicVO> page) {
        return this.baseMapper.searchByKey(page, keyword);
    }

}
