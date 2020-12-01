package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.RolePermissionMapper;
import com.wcy.rhapsody.admin.model.entity.RolePermission;
import com.wcy.rhapsody.admin.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public List<RolePermission> selectByRoleId(Integer roleId) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(RolePermission::getRoleId, roleId);
        return this.baseMapper.selectList(wrapper);
    }
}
