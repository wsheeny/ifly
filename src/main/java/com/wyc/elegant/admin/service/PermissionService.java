package com.wyc.elegant.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.elegant.admin.model.entity.Permission;

import java.util.List;

/**
 * 权限接口
 *
 * @author Yeeep 2020/11/7
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据角色ID查询用户权限
     *
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Integer roleId);
}
