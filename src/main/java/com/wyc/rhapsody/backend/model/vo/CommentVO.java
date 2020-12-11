package com.wyc.rhapsody.backend.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * 评论VO
 *
 * @author Knox 2020/11/13
 */
@Data
public class CommentVO {

    private String id;

    private String content;

    private String topicId;

    private String userId;

    private String avatar;

    private String alias;

    private Date createTime;

    private String commentId;

}
