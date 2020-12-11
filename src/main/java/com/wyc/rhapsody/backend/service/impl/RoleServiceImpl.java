package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.rhapsody.backend.mapper.RoleMapper;
import com.wyc.rhapsody.backend.model.entity.TbRole;
import com.wyc.rhapsody.backend.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, TbRole> implements RoleService {

    @Override
    public TbRole getRoleById(Integer roleId) {
        return this.baseMapper.selectById(roleId);
    }

}
