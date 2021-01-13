package com.wyc.rhapsody.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.rhapsody.backend.model.dto.LoginDTO;
import com.wyc.rhapsody.backend.model.dto.RegisterDTO;
import com.wyc.rhapsody.backend.model.entity.ums.UmsUser;
import com.wyc.rhapsody.backend.model.vo.ProfileVO;

/**
 * 用户
 *
 * @author Knox 2020/11/7
 */
public interface IUmsUserService extends IService<UmsUser> {

    /**
     * 用户登录
     *
     * @param dto
     * @return 生成的JWT的token
     */
    String executeLogin(LoginDTO dto);

    /**
     * 注册功能
     *
     * @param dto
     * @return 注册对象
     */
    UmsUser executeRegister(RegisterDTO dto);

    /**
     * 获取用户信息
     *
     * @param username
     * @return dbUser
     */
    UmsUser getUserByUsername(String username);

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    ProfileVO getUserProfile(String id);

}
