package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.PermissionMapper;
import com.wcy.rhapsody.admin.model.entity.Permission;
import com.wcy.rhapsody.admin.model.entity.RolePermission;
import com.wcy.rhapsody.admin.service.PermissionService;
import com.wcy.rhapsody.admin.service.RolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> selectPermissionsByRoleId(Integer roleId) {
        List<RolePermission> rolePermissions = rolePermissionService.selectByRoleId(roleId);
        List<Integer> permissionIds = rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors
                        .toList());
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(Permission::getId, permissionIds);
        return this.baseMapper.selectList(wrapper);
    }
}
