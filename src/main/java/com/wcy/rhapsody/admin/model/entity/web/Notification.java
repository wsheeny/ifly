package com.wcy.rhapsody.admin.model.entity.web;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yeeep
 */
@Data
@TableName("notification")
@Accessors(chain = true)
public class Notification implements Serializable {
    private static final long serialVersionUID = 3235461023789820336L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("topic_id")
    private String topicId;

    @TableField("user_id")
    private String userId;
    /**
     * 通知对象ID
     */
    @TableField("target_user_id")
    private String targetUserId;
    /**
     * 动作: REPLY, COMMENT, COLLECT, TOPIC_UP, COMMENT_UP
     */
    @TableField("action")
    private String action;

    @TableField(value = "create_Time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField("content")
    private String content;
    /**
     * 是否已读
     */
    @TableField("`read`")
    private Boolean read;
}
