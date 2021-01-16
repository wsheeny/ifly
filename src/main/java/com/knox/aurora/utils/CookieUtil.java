package com.knox.aurora.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Cookie工具
 *
 * @author knox
 */
@Component
public class CookieUtil {


    public static final int MAX_AGE = 60 * 60 * 24;

    /**
     * 设置cookie
     *
     * @param key   键
     * @param value 值
     */
    public void setCookie(String key, String value) {
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getResponse();
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(MAX_AGE);
        // cookie.setDomain();
        assert response != null;
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     *
     * @param name cookie键值
     * @return
     */
    public String getCookie(String name) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equalsIgnoreCase(name)) {
                    return c.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 清除cookie
     *
     * @param name
     */
    public void clearCookie(String name) {
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getResponse();
        Cookie cookie = new Cookie(name, null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        // cookie.setDomain();
        cookie.setPath("/");
        assert response != null;
        response.addCookie(cookie);
    }
}
