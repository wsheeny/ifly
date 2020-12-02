package com.wyc.elegant.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OAuth 授权登录
 *
 * @author Yeeep
 */
@Data
@TableName("oauth_user")
public class OAuthUser implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;


    /**
     * oauth帐号的id
     */
    @TableField("oauth_id")
    private Integer oauthId;

    /**
     * 帐号类型，GITHUB, QQ, WECHAT, WEIBO 等
     */
    @TableField("type")
    private String type;

    /**
     * oauth帐号的登录名
     */
    @TableField("login")
    private String login;

    @JsonIgnore
    @TableField("access_token")
    private String accessToken;

    /**
     * 个人简介
     */
    @TableField("bio")
    private String bio;

    @TableField("email")
    private String email;

    /**
     * 本地用户的id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 刷新token
     */
    @TableField("refresh_token")
    private String refreshToken;

    /**
     * 只微信里有这个字段，联合登录id
     */
    @TableField("union_id")
    private String unionId;

    @TableField(value = "create_Time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * token过期时间，这里用的是String来存的，用时转换一下即可
     * 为啥要用String，因为String可以很方便的转其它类型..
     */
    @TableField("expires_in")
    private Date expiresIn;
}
