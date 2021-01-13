package com.wyc.amor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.amor.mapper.UmsPermissionMapper;
import com.wyc.amor.model.entity.ums.UmsPermission;
import com.wyc.amor.model.entity.ums.UmsRolePermission;
import com.wyc.amor.service.IBmsPermissionService;
import com.wyc.amor.service.IBmsRolePermissionService;
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
public class IBmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements IBmsPermissionService {

    @Autowired
    private IBmsRolePermissionService IBmsRolePermissionService;

    @Override
    public List<UmsPermission> getByRoleId(Integer roleId) {
        List<UmsRolePermission> rolePermissions = IBmsRolePermissionService.selectByRoleId(roleId);
        List<Integer> permissionIds = rolePermissions.stream()
                .map(UmsRolePermission::getPermissionId)
                .collect(Collectors
                        .toList());
        QueryWrapper<UmsPermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(UmsPermission::getId, permissionIds);
        return this.baseMapper.selectList(wrapper);
    }
}
