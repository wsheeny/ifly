package com.wyc.elegant.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import com.wyc.elegant.admin.component.RedisService;
import com.wyc.elegant.admin.mapper.TagMapper;
import com.wyc.elegant.admin.mapper.TopicMapper;
import com.wyc.elegant.admin.mapper.UserMapper;
import com.wyc.elegant.admin.model.dto.CreateTopicDTO;
import com.wyc.elegant.admin.model.entity.*;
import com.wyc.elegant.admin.model.vo.ProfileVO;
import com.wyc.elegant.admin.model.vo.TopicVO;
import com.wyc.elegant.admin.service.TagService;
import com.wyc.elegant.admin.service.TopicService;
import com.wyc.elegant.admin.service.TopicTagService;
import com.wyc.elegant.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
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

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
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
    public Map<String, Object> viewTopic(String id) {
        Map<String, Object> map = new HashMap<>(16);
        Topic topic = this.baseMapper.selectById(id);
        Assert.notNull(topic, "当前话题不存在,或已被作者删除");
        // 查询话题详情
        topic.setView(topic.getView() + 1);
        this.baseMapper.updateById(topic);
        // emoji转码
        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        map.put("topic", topic);
        // 标签
        QueryWrapper<TopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TopicTag::getTopicId, topic.getId());
        Set<String> set = new HashSet<>();
        for (TopicTag articleTag : topicTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<Tag> tags = tagService.listByIds(set);
        map.put("tags", tags);

        // 作者

        ProfileVO user = userService.getUserProfile(topic.getUserId());
        map.put("user", user);

        return map;
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
        Topic topic1 = this.baseMapper.selectOne(
                new LambdaQueryWrapper<Topic>()
                        .eq(Topic::getTitle, dto.getTitle()));
        Assert.isNull(topic1, "话题重复，请修改");

        String dbContent = EmojiParser.parseToAliases(dto.getContent());

        // 封装
        Topic topic = Topic.builder()
                .userId(principal.getId())
                .title(dto.getTitle())
                .content(dbContent)
                .categoryId(dto.getCategoryId())
                .createTime(new Date())
                .build();

        this.baseMapper.insert(topic);

        // 用户积分增加
        int newScore = principal.getScore() + 1;
        userMapper.updateById(principal.setScore(newScore));

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
        topic.setContent(EmojiParser.parseToUnicode(dbContent));
        return topic;
    }

    @Override
    public IPage<TopicVO> selectTopicsByCategory(Category category, Page<TopicVO> page) {
        return this.baseMapper.selectTopicsByCategory(category.getId(), page);
    }

    @Override
    public Page<TopicVO> selectByColumn(Page<TopicVO> page, Column column) {
        return this.baseMapper.selectByColumn(page, column);
    }
}
