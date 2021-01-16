package com.knox.aurora.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knox.aurora.mapper.UmsRoleMapper;
import com.knox.aurora.model.entity.UmsRole;
import com.knox.aurora.service.IUmsRoleService;
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
