package com.wyc.amor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.amor.mapper.UmsRolePermissionMapper;
import com.wyc.amor.model.entity.ums.UmsRolePermission;
import com.wyc.amor.service.IBmsRolePermissionService;
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
