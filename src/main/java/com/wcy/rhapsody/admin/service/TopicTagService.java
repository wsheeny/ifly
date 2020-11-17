package com.wcy.rhapsody.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcy.rhapsody.admin.modules.entity.Tag;
import com.wcy.rhapsody.admin.modules.entity.TopicTag;

import java.util.List;
import java.util.Set;

/**
 * Topic Tag 接口
 *
 * @author Yeeep 2020/11/7
 */
public interface TopicTagService extends IService<TopicTag> {

    /**
     * 获取Topic Tag 关联记录
     *
     * @param topicId TopicId
     * @return
     */
    List<TopicTag> selectByTopicId(String topicId);

    /**
     * 获取标签换脸话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> selectTopicIdsByTagId(String id);

    /**
     * 创建中间关系
     *
     * @param id
     * @param tags
     * @return
     */
    int createTopicTag(String id, List<Tag> tags);
}
