package com.wyc.amor.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.amor.model.dto.CreateTopicDTO;
import com.wyc.amor.model.entity.BmsColumn;
import com.wyc.amor.model.entity.BmsPost;
import com.wyc.amor.model.entity.UmsUser;
import com.wyc.amor.model.vo.PostVO;

import java.util.List;
import java.util.Map;

/**
 * 议题接口
 *
 * @author Knox 2020/11/7
 */
public interface IBmsPostService extends IService<BmsPost> {

    /**
     * 获取首页话题列表
     *
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> getList(Page<PostVO> page, String tab);


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
    List<BmsPost> selectAuthorOtherTopic(String userId, String topicId);

    /**
     * 用户主页：查询用户的话题，10篇
     *
     * @param userId
     * @param page
     * @return
     */
    Page<BmsPost> selectTopicsByUserId(String userId, Page<BmsPost> page);

    /**
     * 获取随机推荐10篇
     *
     * @param id
     * @return
     */
    List<BmsPost> getRecommend(String id);

    /**
     * 发布
     *
     * @param dto
     * @param principal
     * @return
     */
    BmsPost create(CreateTopicDTO dto, UmsUser principal);

    /**
     * 专栏检索
     *
     * @param page
     * @param column
     * @return
     */
    Page<PostVO> selectByColumn(Page<PostVO> page, BmsColumn column);

    /**
     * 关键字检索
     *
     * @param keyword
     * @param page
     * @return
     */
    Page<PostVO> searchByKey(String keyword, Page<PostVO> page);
}
