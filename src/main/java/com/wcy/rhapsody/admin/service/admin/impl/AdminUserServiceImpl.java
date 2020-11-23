package com.wcy.rhapsody.admin.service.admin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.admin.AdminUserMapper;
import com.wcy.rhapsody.admin.modules.entity.admin.AdminUser;
import com.wcy.rhapsody.admin.service.admin.AdminUserService;
import org.springframework.stereotype.Service;

/**
 * @author Yeeep 2020/11/23
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

}
