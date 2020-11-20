package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.config.redis.RedisService;
import com.wcy.rhapsody.admin.mapper.TagMapper;
import com.wcy.rhapsody.admin.mapper.TopicMapper;
import com.wcy.rhapsody.admin.modules.dto.CreateTopicDTO;
import com.wcy.rhapsody.admin.modules.entity.*;
import com.wcy.rhapsody.admin.modules.vo.TopicVO;
import com.wcy.rhapsody.admin.service.TagService;
import com.wcy.rhapsody.admin.service.TopicService;
import com.wcy.rhapsody.admin.service.TopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 议题，话题实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Autowired
    private RedisService redisService;


    @Resource
    private TagMapper tagMapper;


    @Autowired
    @Lazy
    private TagService tagService;

    @Autowired
    private TopicTagService topicTagService;

    @Override
    public Page<TopicVO> getTopicListAndPage(Page<TopicVO> page, String tab) {
        // 查询话题
        Page<TopicVO> iPage = this.baseMapper.selectListAndPage(page, tab);

        // 查询话题的标签
        iPage.getRecords().forEach(topic -> {
            List<TopicTag> topicTags = topicTagService.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(TopicTag::getTagId).collect(Collectors.toList());
                List<Tag> tags = tagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
        return iPage;
    }

    @Override
    public List<Topic> selectAuthorOtherTopic(String userId, String topicId) {
        List<Topic> topics = (List<Topic>) redisService.get("otherTopics");

        if (StringUtils.isEmpty(topics)) {
            QueryWrapper<Topic> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(Topic::getUserId, userId).orderByDesc(Topic::getCreateTime);
            if (topicId != null) {
                wrapper.lambda().ne(Topic::getId, topicId);
            }
            wrapper.last("limit " + 10);
            topics = this.baseMapper.selectList(wrapper);
            // 缓存
            redisService.set("otherTopics", topics, 60 * 60);
        }
        return topics;
    }

    @Override
    public Page<Topic> selectTopicsByUserId(String userId, Page<Topic> page) {
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Topic::getUserId, userId);
        Page<Topic> topicPage = this.baseMapper.selectPage(page, wrapper);


        return topicPage;
    }

    @Override
    public List<Topic> getRecommend(String id) {
        return this.baseMapper.selectRecommend(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Topic create(CreateTopicDTO dto, User principal) {
        // 封装
        Topic topic = Topic.builder()
                .userId(principal.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .categoryId(dto.getCategoryId())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(topic);

        // 标签
        if (!StringUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<Tag> tags = tagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            topicTagService.createTopicTag(topic.getId(), tags);
        }

        // TODO: 2020/11/14 ES索引话题
        // indexedService.indexTopic(String.valueOf(topic.getId()), topic.getTitle(), topic.getContent());

        redisService.del("getTopicListAndPage");

        return topic;
    }

    @Override
    public IPage<TopicVO> selectTopicsByCategory(Category category, Page<TopicVO> page) {
        return this.baseMapper.selectTopicsByCategory(category.getId(), page);
    }
}
