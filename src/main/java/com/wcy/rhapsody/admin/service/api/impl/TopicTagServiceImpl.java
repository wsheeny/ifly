package com.wcy.rhapsody.admin.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.api.TopicTagMapper;
import com.wcy.rhapsody.admin.model.entity.web.Tag;
import com.wcy.rhapsody.admin.model.entity.web.TopicTag;
import com.wcy.rhapsody.admin.service.api.TopicTagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Topic Tag 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class TopicTagServiceImpl extends ServiceImpl<TopicTagMapper, TopicTag> implements TopicTagService {

    @Override
    public List<TopicTag> selectByTopicId(String topicId) {
        QueryWrapper<TopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TopicTag::getTopicId, topicId);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public Set<String> selectTopicIdsByTagId(String id) {
        return this.baseMapper.getTopicIdsByTagId(id);
    }

    @Override
    public int createTopicTag(String id, List<Tag> tags) {
        // 先删除topicId对应的所有记录
        this.baseMapper.delete(new LambdaQueryWrapper<TopicTag>().eq(TopicTag::getTopicId, id));

        // 循环保存对应关联
        tags.forEach(tag -> {
            TopicTag topicTag = new TopicTag();
            topicTag.setTopicId(id);
            topicTag.setTagId(tag.getId());
            this.baseMapper.insert(topicTag);
        });
        return 0;
    }
}
