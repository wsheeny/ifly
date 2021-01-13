package com.wyc.rhapsody.backend.model.entity.ums;

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
 * @author Knox
 */
@Data
@TableName("ums_role")
@Accessors(chain = true)
public class UmsRole implements Serializable {
    private static final long serialVersionUID = 7824693669858106664L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("remark")
    private String remark;
}
