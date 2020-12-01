package com.wcy.rhapsody.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcy.rhapsody.admin.model.entity.RolePermission;

import java.util.List;

/**
 * 角色-权限接口
 *
 * @author Yeeep 2020/11/7
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 查询角色的权限关联记录
     *
     * @param roleId
     * @return
     */
    List<RolePermission> selectByRoleId(Integer roleId);
}
