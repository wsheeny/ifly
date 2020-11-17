package com.wcy.rhapsody.admin.modules.dto;

import lombok.Data;

/**
 * 评论DTO
 *
 * @author Yeeep 2020/11/13
 */
@Data
public class CommentDTO {

    // 评论关联话题
    private String topicId;

    // 评论内容
    private String content;

    // 被回复人评论ID
    private String commentId;
}
