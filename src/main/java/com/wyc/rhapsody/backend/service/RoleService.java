package com.wyc.rhapsody.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.rhapsody.backend.model.entity.TbRole;

/**
 * 角色 接口
 *
 * @author Knox 2020/11/7
 */
public interface RoleService extends IService<TbRole> {

    /**
     * 获取角色
     *
     * @param roleId
     * @return
     */
    TbRole getRoleById(Integer roleId);
}
