package com.wcy.rhapsody.admin.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色
 *
 * @author Yeeep
 */
@Data
@TableName("role")
@Accessors(chain = true)
public class Role implements Serializable {
    private static final long serialVersionUID = 7824693669858106664L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;
}
