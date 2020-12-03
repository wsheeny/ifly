package com.wyc.elegant.admin.controller;

import java.security.Principal;

/**
 * 基类
 *
 * @author Knox 2020/11/7
 */
public class BaseController {

    /**
     * 获取登录用户名
     *
     * @param principal
     * @return
     */
    public String getUserName(Principal principal) {
        return principal.getName();
    }
}
