package com.wcy.rhapsody.admin.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcy.rhapsody.admin.model.entity.web.Tag;
import com.wcy.rhapsody.admin.model.entity.web.Topic;

import java.util.List;

/**
 * Tag 接口
 *
 * @author Yeeep 2020/11/7
 */
public interface TagService extends IService<Tag> {
    /**
     * 插入标签
     *
     * @param tags
     * @return
     */
    List<Tag> insertTags(List<String> tags);

    /**
     * 获取标签关联话题
     *
     * @param topicPage
     * @param id
     * @return
     */
    Page<Topic> selectTopicsByTagId(Page<Topic> topicPage, String id);


}
