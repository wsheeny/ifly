package com.wyc.elegant.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.mapper.PermissionMapper;
import com.wyc.elegant.admin.model.entity.TbPermission;
import com.wyc.elegant.admin.model.entity.TbRolePermission;
import com.wyc.elegant.admin.service.PermissionService;
import com.wyc.elegant.admin.service.RolePermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, TbPermission> implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<TbPermission> selectPermissionsByRoleId(Integer roleId) {
        List<TbRolePermission> rolePermissions = rolePermissionService.selectByRoleId(roleId);
        List<Integer> permissionIds = rolePermissions.stream()
                .map(TbRolePermission::getPermissionId)
                .collect(Collectors
                        .toList());
        QueryWrapper<TbPermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(TbPermission::getId, permissionIds);
        return this.baseMapper.selectList(wrapper);
    }
}
