package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.AdminUserMapper;
import com.wcy.rhapsody.admin.model.entity.AdminUser;
import com.wcy.rhapsody.admin.service.AdminUserService;
import org.springframework.stereotype.Service;

/**
 * @author Yeeep
 * @date 2020/12/1
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

}
