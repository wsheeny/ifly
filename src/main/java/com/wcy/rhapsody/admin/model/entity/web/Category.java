package com.wcy.rhapsody.admin.model.entity.web;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 分类
 *
 * @author Yeeep 2020/11/15
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名
     */
    @TableField("name")
    private String name;

    /**
     * 配图
     */
    @TableField("icon")
    private String icon;

    /**
     * 描述
     */
    @TableField("`description`")
    private String description;

    public Category() {
    }

}