package com.wcy.rhapsody.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcy.rhapsody.admin.modules.dto.CommentDTO;
import com.wcy.rhapsody.admin.modules.entity.Comment;
import com.wcy.rhapsody.admin.modules.entity.User;
import com.wcy.rhapsody.admin.modules.vo.CommentVO;

import java.util.List;

/**
 * 评论
 *
 * @author Yeeep 2020/11/7
 */
public interface CommentService extends IService<Comment> {

    /**
     * 发表评论
     *
     * @param dto
     * @param user
     * @return
     */
    Comment insert(CommentDTO dto, User user);

    /**
     * 主题评论
     *
     * @param topicId
     * @return
     */
    List<CommentVO> getCommentsByTopicId(String topicId);
}
