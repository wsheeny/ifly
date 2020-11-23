package com.wcy.rhapsody.admin.modules.entity.web;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author Yeeep
 */
@Data
@TableName("user")
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = -5051120337175047163L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 用户名
     */
    @TableField("username")
    private String username;
    /**
     * 昵称
     */
    @TableField("alias")
    private String alias;
    /**
     * 密码, 敏感数据不返给前端
     */
    @JsonIgnore()
    @TableField("password")
    private String password;
    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 个人网站
     */
    @TableField("website")
    private String website;
    /**
     * 个人简介
     */
    @TableField("bio")
    private String bio;
    /**
     * 签名
     */
    @TableField("signature")
    private String signature;
    /**
     * 积分
     */
    @TableField("score")
    private Integer score;

    /**
     * token
     */
    @TableField("token")
    private String token;
    /**
     * 帐号是否激活
     */
    @TableField("active")
    private Boolean active;
    /**
     * Twitter账号
     */
    @TableField("twitter_name")
    private String twitter;
    /**
     * 有消息通知是否通过邮箱收取
     */
    @TableField("email_notification")
    private Boolean emailNotification;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
}
