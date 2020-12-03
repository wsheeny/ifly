package com.wyc.elegant.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.mapper.TopicTagMapper;
import com.wyc.elegant.admin.model.entity.TbTag;
import com.wyc.elegant.admin.model.entity.TbTopicTag;
import com.wyc.elegant.admin.service.TopicTagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Topic Tag 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class TopicTagServiceImpl extends ServiceImpl<TopicTagMapper, TbTopicTag> implements TopicTagService {

    @Override
    public List<TbTopicTag> selectByTopicId(String topicId) {
        QueryWrapper<TbTopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TbTopicTag::getTopicId, topicId);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public Set<String> selectTopicIdsByTagId(String id) {
        return this.baseMapper.getTopicIdsByTagId(id);
    }

    @Override
    public int createTopicTag(String id, List<TbTag> tags) {
        // 先删除topicId对应的所有记录
        this.baseMapper.delete(new LambdaQueryWrapper<TbTopicTag>().eq(TbTopicTag::getTopicId, id));

        // 循环保存对应关联
        tags.forEach(tag -> {
            TbTopicTag topicTag = new TbTopicTag();
            topicTag.setTopicId(id);
            topicTag.setTagId(tag.getId());
            this.baseMapper.insert(topicTag);
        });
        return 0;
    }
}
