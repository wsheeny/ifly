package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.rhapsody.backend.mapper.RolePermissionMapper;
import com.wyc.rhapsody.backend.model.entity.ums.UmsRolePermission;
import com.wyc.rhapsody.backend.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, UmsRolePermission> implements RolePermissionService {

    @Override
    public List<UmsRolePermission> selectByRoleId(Integer roleId) {
        QueryWrapper<UmsRolePermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsRolePermission::getRoleId, roleId);
        return this.baseMapper.selectList(wrapper);
    }
}
