package com.knox.aurora.model.vo;

import lombok.Data;

/**
 * 用户数据
 *
 * @author Knox 2020/11/20
 */
@Data
public class ProfileVO {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 别称
     */
    private String alias;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 关注数
     */
    private Integer followCount;

    /**
     * 关注者数
     */
    private Integer followerCount;

    /**
     * 文章数
     */
    private Integer topicCount;

    /**
     * 专栏数
     */
    private Integer columns;

    /**
     * 评论数
     */
    private Integer commentCount;

}
