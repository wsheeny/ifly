package com.wcy.rhapsody.admin.modules.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Yeeep
 */
@Data
@TableName("role_permission")
@Accessors(chain = true)
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 6841432556002220108L;

    @TableField("role_id")
    private Integer roleId;

    @TableField("permission_id")
    private Integer permissionId;
}
