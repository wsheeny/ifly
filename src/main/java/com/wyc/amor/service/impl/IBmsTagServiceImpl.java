package com.wyc.amor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.amor.mapper.BmsTagMapper;
import com.wyc.amor.model.entity.TbPost;
import com.wyc.amor.model.entity.TbTag;
import com.wyc.amor.service.IBmsPostService;
import com.wyc.amor.service.IBmsTagService;
import com.wyc.amor.service.IBmsTopicTagService;
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
public class IBmsTagServiceImpl extends ServiceImpl<BmsTagMapper, TbTag> implements IBmsTagService {

    @Autowired
    private IBmsTopicTagService IBmsTopicTagService;

    @Autowired
    private IBmsPostService IBmsPostService;

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
    public Page<TbPost> selectTopicsByTagId(Page<TbPost> topicPage, String id) {

        // 获取关联的话题ID
        Set<String> ids = IBmsTopicTagService.selectTopicIdsByTagId(id);
        LambdaQueryWrapper<TbPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(TbPost::getId, ids);

        return IBmsPostService.page(topicPage, wrapper);
    }

}
