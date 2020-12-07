package com.wyc.buefy.web.controller;

import com.wyc.buefy.web.config.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 基类
 *
 * @author Knox 2020/11/7
 */
public class BaseController {

    /**
     * 获取登录信息，重写的UserDetails
     *
     * @return
     */
    public MyUserDetails getMyUserDetails() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
