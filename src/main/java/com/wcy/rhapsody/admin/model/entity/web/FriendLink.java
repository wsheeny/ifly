package com.wcy.rhapsody.admin.model.entity.web;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 友情链接
 *
 * @author Yeeep
 * @create 2020/10/20
 */
@Data
@TableName("friend_link")
@Accessors(chain = true)
public class FriendLink implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("title")
    private String title;

    /**
     * 跳转链接
     */
    @TableField("link")
    private String link;

    /**
     * 展示logo
     */
    @TableField("icon")
    private String icon;

    /**
     * 1：链接中，0-已取消链接
     */
    @TableField("state")
    private Boolean state;


    public FriendLink() {

    }

}