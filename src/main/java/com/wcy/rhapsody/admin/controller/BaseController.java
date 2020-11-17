package com.wcy.rhapsody.admin.controller;

import com.wcy.rhapsody.admin.config.shiro.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 基类
 *
 * @author Yeeep 2020/11/7
 */
public class BaseController extends ShiroUtil {

    /**
     * 获取shiro subject对象
     *
     * @return Subject
     */
    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
