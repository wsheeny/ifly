package com.wcy.rhapsody.admin.modules.entity.web;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 激活码
 *
 * @author Yeeep
 */
@Data
@TableName("active_code")
@Accessors(chain = true)
public class ActiveCode implements Serializable {

    private static final long serialVersionUID = -6008460350253418202L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 关联是哪个用户的验证码
     */
    @TableField("user_id")
    private String userId;
    /**
     * 邮件链接里附带上的验证码
     */
    @TableField("code")
    private String code;
    /**
     * 激活码生成时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 过期时间，默认30分钟失效
     */
    @TableField(value = "expire_time", fill = FieldFill.INSERT_UPDATE)
    private Date expireTime;
    /**
     * 要修改的邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 是否使用过
     */
    @TableField("used")
    private Boolean used;
}
