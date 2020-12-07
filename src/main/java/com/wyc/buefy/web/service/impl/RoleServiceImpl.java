package com.wyc.buefy.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.buefy.web.mapper.RoleMapper;
import com.wyc.buefy.web.model.entity.TbRole;
import com.wyc.buefy.web.service.RoleService;
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
