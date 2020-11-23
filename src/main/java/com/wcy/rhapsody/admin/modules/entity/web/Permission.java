package com.wcy.rhapsody.admin.modules.entity.web;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限
 *
 * @author Yeeep
 */
@Data
@TableName("permission")
@Accessors(chain = true)
public class Permission implements Serializable {
    private static final long serialVersionUID = -2694960432845360318L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("remark")
    private String remark;

    /**
     * 权限的父节点的id
     */
    @TableField("pid")
    private Integer pid;
}
