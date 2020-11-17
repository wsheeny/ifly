package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.RoleMapper;
import com.wcy.rhapsody.admin.modules.entity.Role;
import com.wcy.rhapsody.admin.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
