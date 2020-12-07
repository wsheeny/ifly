package com.wyc.buefy.web.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 关注列表
 *
 * @author Knox 2020/11/20
 */
@Data
@TableName("tb_follow")
public class TbFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 被关注人id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 关注人id
     */
    @TableField("follower_id")
    private String followerId;

    public TbFollow() {
    }

}
