package com.wcy.rhapsody.admin.modules.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户数据
 *
 * @author Yeeep 2020/11/20
 */
@Data
@NoArgsConstructor
public class ProfileVO implements Serializable {

    private static final long serialVersionUID = -485222016506568799L;

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
     * 评论数
     */
    private Integer commentCount;

}
