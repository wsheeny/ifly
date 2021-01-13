package com.wyc.amor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.amor.mapper.UmsRoleMapper;
import com.wyc.amor.model.entity.UmsRole;
import com.wyc.amor.service.IUmsRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class IUmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

    @Override
    public UmsRole getRoleById(Integer roleId) {
        return this.baseMapper.selectById(roleId);
    }

}
