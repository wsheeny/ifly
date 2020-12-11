package com.wyc.rhapsody.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.rhapsody.backend.model.dto.CreateTopicDTO;
import com.wyc.rhapsody.backend.model.entity.TbColumn;
import com.wyc.rhapsody.backend.model.entity.TbTopic;
import com.wyc.rhapsody.backend.model.entity.TbUser;
import com.wyc.rhapsody.backend.model.vo.TopicVO;

import java.util.List;
import java.util.Map;

/**
 * 议题接口
 *
 * @author Knox 2020/11/7
 */
public interface TopicService extends IService<TbTopic> {

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
    List<TbTopic> selectAuthorOtherTopic(String userId, String topicId);

    /**
     * 用户主页：查询用户的话题，10篇
     *
     * @param userId
     * @param page
     * @return
     */
    Page<TbTopic> selectTopicsByUserId(String userId, Page<TbTopic> page);

    /**
     * 获取随机推荐10篇
     *
     * @param id
     * @return
     */
    List<TbTopic> getRecommend(String id);

    /**
     * 发布
     *
     * @param dto
     * @param principal
     * @return
     */
    TbTopic create(CreateTopicDTO dto, TbUser principal);

    /**
     * 专栏检索
     *
     * @param page
     * @param column
     * @return
     */
    Page<TopicVO> selectByColumn(Page<TopicVO> page, TbColumn column);

    /**
     * 关键字检索
     *
     * @param keyword
     * @param page
     * @return
     */
    Page<TopicVO> searchByKey(String keyword, Page<TopicVO> page);
}
