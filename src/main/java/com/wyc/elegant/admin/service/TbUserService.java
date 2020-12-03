package com.wyc.elegant.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.elegant.admin.model.dto.LoginDTO;
import com.wyc.elegant.admin.model.dto.RegisterDTO;
import com.wyc.elegant.admin.model.entity.TbPermission;
import com.wyc.elegant.admin.model.entity.TbUser;
import com.wyc.elegant.admin.model.vo.ProfileVO;

import java.util.List;

/**
 * 用户
 *
 * @author Knox 2020/11/7
 */
public interface TbUserService extends IService<TbUser> {
    /**
     * 获取用户信息
     *
     * @param username
     * @return dbUser
     */
    TbUser getUserByUsername(String username);

    /**
     * 注册功能
     *
     * @param dto
     * @return 注册对象
     */
    TbUser register(RegisterDTO dto);

    /**
     * 登录功能
     *
     * @param dto 登录DTO
     * @return 生成的JWT的token
     */
    String login(LoginDTO dto);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     *
     * @param userId
     * @return
     */
    List<TbPermission> getPermissionList(String userId);


    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    ProfileVO getUserProfile(String id);

}
