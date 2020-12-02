package com.wyc.elegant.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.elegant.admin.model.entity.AdminUser;
import com.wyc.elegant.admin.model.entity.Permission;

import java.util.List;

/**
 * 后台系统用户接口
 *
 * @author Yeeep 2020/11/23
 */
public interface AdminUserService extends IService<AdminUser> {

    /**
     * 获取用户权限集合
     *
     * @param id 用户ID
     * @return
     */
    List<Permission> getPermissionList(Integer id);

    /**
     * 获取后台账户
     *
     * @param username
     * @return
     */
    AdminUser getAdminUserByUsername(String username);
}
