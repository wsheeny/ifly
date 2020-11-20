package com.wcy.rhapsody.admin.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * shiro标签
 *
 * @author Yeeep
 */
@Component
public class ShiroUtil {

    /**
     * 当前用户是否已经登录认证过
     *
     * @return
     */
    public boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    /**
     * 判断用户是否有 xx 角色
     *
     * @param name
     * @return
     */
    public boolean hasRole(String name) {
        return SecurityUtils.getSubject().hasRole(name);
    }

    /**
     * 判断用户是否有 xx 权限
     *
     * @param name
     * @return
     */
    public boolean hasPermission(String name) {
        return !StringUtils.isEmpty(name) && SecurityUtils.getSubject().isPermitted(name);
    }

    /**
     * 判断用户是否有 xx 权限
     *
     * @param name
     * @return
     */
    public boolean hasPermissionOr(String... name) {
        boolean[] permitted = SecurityUtils.getSubject().isPermitted(name);
        for (boolean b : permitted) {
            // 如果有一个权限，就成功
            if (b) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户是否有 xx 权限
     *
     * @param name
     * @return
     */
    public boolean hasPermissionAnd(String... name) {
        boolean[] permitted = SecurityUtils.getSubject().isPermitted(name);
        for (boolean b : permitted) {
            // 必须所有的权限都有，才成功
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断用户是否有 xx 权限
     *
     * @param name
     * @return
     */
    public boolean hasAllPermission(String... name) {
        return SecurityUtils.getSubject().isPermittedAll(name);
    }
}
