package com.wyc.rhapsody.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.rhapsody.backend.model.entity.ums.UmsRolePermission;

import java.util.List;

/**
 * 角色-权限接口
 *
 * @author Knox 2020/11/7
 */
public interface RolePermissionService extends IService<UmsRolePermission> {

    /**
     * 查询角色的权限关联记录
     *
     * @param roleId
     * @return
     */
    List<UmsRolePermission> selectByRoleId(Integer roleId);
}
