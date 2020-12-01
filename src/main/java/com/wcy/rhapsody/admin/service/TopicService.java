package com.wcy.rhapsody.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcy.rhapsody.admin.model.dto.CreateTopicDTO;
import com.wcy.rhapsody.admin.model.entity.Category;
import com.wcy.rhapsody.admin.model.entity.Column;
import com.wcy.rhapsody.admin.model.entity.Topic;
import com.wcy.rhapsody.admin.model.entity.User;
import com.wcy.rhapsody.admin.model.vo.TopicVO;

import java.util.List;
import java.util.Map;

/**
 * 议题接口
 *
 * @author Yeeep 2020/11/7
 */
public interface TopicService extends IService<Topic> {

    /**
     * 获取首页话题列表
     *
     * @param page
     * @param tab
     * @return
     */
    Page<TopicVO> getList(Page<TopicVO> page, String tab);


    /**
     * 查看话题详情
     *
     * @param id
     * @return
     */
    Map<String, Object> viewTopic(String id);

    /**
     * 查询当前作者其他话题 ，10篇
     *
     * @param userId
     * @param topicId
     * @return
     */
    List<Topic> selectAuthorOtherTopic(String userId, String topicId);

    /**
     * 用户主页：查询用户的话题，10篇
     *
     * @param userId
     * @param page
     * @return
     */
    Page<Topic> selectTopicsByUserId(String userId, Page<Topic> page);

    /**
     * 获取随机推荐10篇
     *
     * @param id
     * @return
     */
    List<Topic> getRecommend(String id);

    /**
     * 发布
     *
     * @param dto
     * @param principal
     * @return
     */
    Topic create(CreateTopicDTO dto, User principal);

    /**
     * 查询指定类目下的主题
     *
     * @param category
     * @param topicVOPage
     * @return
     */
    IPage<TopicVO> selectTopicsByCategory(Category category, Page<TopicVO> topicVOPage);

    /**
     * 专栏检索
     *
     * @param page
     * @param column
     * @return
     */
    Page<TopicVO> selectByColumn(Page<TopicVO> page, Column column);
}
