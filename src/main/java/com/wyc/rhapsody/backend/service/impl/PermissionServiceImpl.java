package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.rhapsody.backend.mapper.PermissionMapper;
import com.wyc.rhapsody.backend.model.entity.ums.UmsPermission;
import com.wyc.rhapsody.backend.model.entity.ums.UmsRolePermission;
import com.wyc.rhapsody.backend.service.PermissionService;
import com.wyc.rhapsody.backend.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, UmsPermission> implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<UmsPermission> getByRoleId(Integer roleId) {
        List<UmsRolePermission> rolePermissions = rolePermissionService.selectByRoleId(roleId);
        List<Integer> permissionIds = rolePermissions.stream()
                .map(UmsRolePermission::getPermissionId)
                .collect(Collectors
                        .toList());
        QueryWrapper<UmsPermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(UmsPermission::getId, permissionIds);
        return this.baseMapper.selectList(wrapper);
    }
}
