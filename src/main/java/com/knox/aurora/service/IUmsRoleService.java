package com.knox.aurora.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knox.aurora.model.entity.UmsRole;

/**
 * 角色 接口
 *
 * @author Knox 2020/11/7
 */
public interface IUmsRoleService extends IService<UmsRole> {

    /**
     * 获取角色
     *
     * @param roleId
     * @return
     */
    UmsRole getRoleById(Integer roleId);
}
