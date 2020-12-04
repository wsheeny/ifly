package com.wyc.elegant.admin.controller;

import com.wyc.elegant.admin.model.entity.TbUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 基类
 *
 * @author Knox 2020/11/7
 */
public class BaseController {

    /**
     * 判断是否登录
     *
     * @return
     */
    public Boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    /**
     * 获取shiro的subject对象
     *
     * @return
     */
    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取登录用户
     *
     * @return
     */
    public TbUser getLoginProfile() {
        return (TbUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取session
     *
     * @return
     */
    public Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }
}
