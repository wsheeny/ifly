package com.wyc.buefy.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.buefy.web.model.entity.TbRolePermission;

import java.util.List;

/**
 * 角色-权限接口
 *
 * @author Knox 2020/11/7
 */
public interface RolePermissionService extends IService<TbRolePermission> {

    /**
     * 查询角色的权限关联记录
     *
     * @param roleId
     * @return
     */
    List<TbRolePermission> selectByRoleId(Integer roleId);
}
