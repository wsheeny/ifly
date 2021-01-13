package com.wyc.rhapsody.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.rhapsody.backend.model.entity.TbPost;
import com.wyc.rhapsody.backend.model.entity.TbTag;

import java.util.List;

/**
 * Tag 接口
 *
 * @author Knox 2020/11/7
 */
public interface TagService extends IService<TbTag> {
    /**
     * 插入标签
     *
     * @param tags
     * @return
     */
    List<TbTag> insertTags(List<String> tags);

    /**
     * 获取标签关联话题
     *
     * @param topicPage
     * @param id
     * @return
     */
    Page<TbPost> selectTopicsByTagId(Page<TbPost> topicPage, String id);


}
