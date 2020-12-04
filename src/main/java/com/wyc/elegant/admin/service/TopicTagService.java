package com.wyc.elegant.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.elegant.admin.model.entity.TbTag;
import com.wyc.elegant.admin.model.entity.TbTopicTag;

import java.util.List;
import java.util.Set;

/**
 * Topic Tag 接口
 *
 * @author Knox 2020/11/7
 */
public interface TopicTagService extends IService<TbTopicTag> {

    /**
     * 获取Topic Tag 关联记录
     *
     * @param topicId TopicId
     * @return
     */
    List<TbTopicTag> selectByTopicId(String topicId);

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
    int createTopicTag(String id, List<TbTag> tags);
}