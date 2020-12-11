package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.rhapsody.backend.mapper.TagMapper;
import com.wyc.rhapsody.backend.model.entity.TbTag;
import com.wyc.rhapsody.backend.model.entity.TbTopic;
import com.wyc.rhapsody.backend.service.TagService;
import com.wyc.rhapsody.backend.service.TopicService;
import com.wyc.rhapsody.backend.service.TopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Tag 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TbTag> implements TagService {

    @Autowired
    private TopicTagService topicTagService;

    @Autowired
    private TopicService topicService;

    @Override
    public List<TbTag> insertTags(List<String> tagNames) {
        List<TbTag> tagList = new ArrayList<>();
        for (String tagName : tagNames) {
            TbTag tag = this.baseMapper.selectOne(new LambdaQueryWrapper<TbTag>().eq(TbTag::getName, tagName));
            if (tag == null) {
                tag = TbTag.builder().name(tagName).build();
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
    public Page<TbTopic> selectTopicsByTagId(Page<TbTopic> topicPage, String id) {

        // 获取关联的话题ID
        Set<String> ids = topicTagService.selectTopicIdsByTagId(id);
        LambdaQueryWrapper<TbTopic> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(TbTopic::getId, ids);

        return topicService.page(topicPage, wrapper);
    }

}
