package com.knox.aurora.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knox.aurora.mapper.UmsRolePermissionMapper;
import com.knox.aurora.model.entity.UmsRolePermission;
import com.knox.aurora.service.IBmsRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class IBmsRolePermissionServiceImpl extends ServiceImpl<UmsRolePermissionMapper, UmsRolePermission> implements IBmsRolePermissionService {

    @Override
    public List<UmsRolePermission> selectByRoleId(Integer roleId) {
        QueryWrapper<UmsRolePermission> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsRolePermission::getRoleId, roleId);
        return this.baseMapper.selectList(wrapper);
    }
}
