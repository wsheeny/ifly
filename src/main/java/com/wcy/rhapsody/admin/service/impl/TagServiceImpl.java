package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.TagMapper;
import com.wcy.rhapsody.admin.modules.entity.Tag;
import com.wcy.rhapsody.admin.modules.entity.Topic;
import com.wcy.rhapsody.admin.service.TagService;
import com.wcy.rhapsody.admin.service.TopicService;
import com.wcy.rhapsody.admin.service.TopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Tag 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TopicTagService topicTagService;

    @Autowired
    private TopicService topicService;

    @Override
    public List<Tag> insertTags(List<String> tagNames) {
        List<Tag> tagList = new ArrayList<>();
        for (String tagName : tagNames) {
            Tag tag = this.baseMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName, tagName));
            if (tag == null) {
                tag = Tag.builder().name(tagName).build();
                this.baseMapper.insert(tag);
            } else {
                tag.setTopicCount(tag.getTopicCount() + 1);
                this.baseMapper.updateById(tag);
            }
            tagList.add(tag);
        }
        return tagList;
    }

    @Override
    public Page<Topic> selectTopicsByTagId(Page<Topic> topicPage, String id) {

        // 获取关联的话题ID
        Set<String> ids = topicTagService.selectTopicIdsByTagId(id);
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Topic::getId, ids);

        return topicService.page(topicPage, wrapper);
    }

}
