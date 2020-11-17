package com.wcy.rhapsody.admin.modules.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Yeeep
 */
@Data
@TableName("comment")
@Accessors(chain = true)
public class Comment implements Serializable {
    private static final long serialVersionUID = 8413239906874427490L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("topic_id")
    private String topicId;

    @TableField("user_id")
    private String userId;

    @TableField("content")
    private String content;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField("comment_id")
    private String commentId;

    /**
     * 点赞用户的id
     */
    @TableField("up_ids")
    private String upIds;
}
