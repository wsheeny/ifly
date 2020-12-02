package com.wyc.elegant.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.elegant.admin.model.dto.RegisterDTO;
import com.wyc.elegant.admin.model.entity.User;
import com.wyc.elegant.admin.model.vo.ProfileVO;

/**
 * 用户
 *
 * @author Yeeep 2020/11/7
 */
public interface UserService extends IService<User> {

    /**
     * shiro登录：用户名验证账号是否存在
     *
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 创建用户
     *
     * @param dto
     * @return
     */
    void createUser(RegisterDTO dto);

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    ProfileVO getUserProfile(String id);
}
