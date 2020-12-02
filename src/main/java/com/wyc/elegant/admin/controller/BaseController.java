package com.wyc.elegant.admin.controller;

import com.wyc.elegant.admin.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 基类
 *
 * @author Yeeep 2020/11/7
 */
public class BaseController {

    /**
     * 获取shiro subject对象
     *
     * @return Subject
     */
    // public Subject getSubject() {
    //     return SecurityUtils.getSubject();
    // }

    /**
     * 获取session登录信息
     */
    public User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("loginUser");
    }

}
