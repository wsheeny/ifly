package com.wcy.rhapsody.admin.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.api.RolePermissionMapper;
import com.wcy.rhapsody.admin.modules.entity.web.RolePermission;
import com.wcy.rhapsody.admin.service.api.RolePermissionService;
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
