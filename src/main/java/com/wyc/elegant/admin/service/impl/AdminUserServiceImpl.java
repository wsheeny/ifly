package com.wyc.elegant.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.mapper.AdminUserMapper;
import com.wyc.elegant.admin.model.entity.AdminUser;
import com.wyc.elegant.admin.model.entity.Permission;
import com.wyc.elegant.admin.service.AdminUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台账户
 *
 * @author Yeeep
 * @date 2020/12/1
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Override
    public List<Permission> getPermissionList(Integer id) {
        return null;
    }

    @Override
    public AdminUser getAdminUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, username));
    }
}
