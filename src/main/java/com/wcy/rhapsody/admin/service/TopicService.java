package com.wcy.rhapsody.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcy.rhapsody.admin.modules.dto.CreateTopicDTO;
import com.wcy.rhapsody.admin.modules.entity.Category;
import com.wcy.rhapsody.admin.modules.entity.Topic;
import com.wcy.rhapsody.admin.modules.entity.User;
import com.wcy.rhapsody.admin.modules.vo.TopicVO;

import java.util.List;

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
    Page<TopicVO> getTopicListAndPage(Page<TopicVO> page, String tab);


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
     * @return
     */
    List<Topic> getRecommend();

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
}
