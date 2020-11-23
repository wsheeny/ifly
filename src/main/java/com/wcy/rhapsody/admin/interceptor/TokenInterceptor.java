package com.wcy.rhapsody.admin.interceptor;

import com.wcy.rhapsody.admin.config.jwt.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT TOKEN拦截器
 *
 * @author Yeeep 2020/11/23
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头（如果有此请求头，表示token已经签发）
        String token = request.getHeader("X-Token");
        if (!StringUtils.isEmpty(token)) {
            Claims claims = jwtTokenUtil.parseToken(token);


        }

        // if (header != null || !"".equals(header)) {
        //     //解析请求头（防止伪造token，token内容以"token "作为开头）
        //     if (header.startsWith("token ")) {
        //         try {
        //             Claims claims = jwtTokenUtil.parseToken(header.substring(6));
        //             String role = (String) claims.get("role");
        //             //为具有相关权限的用户添加权限到request域中
        //             if ("admin".equals(role)) {
        //                 //拿到"admin_token"头信息，表示当前角色是admin
        //                 request.setAttribute("admin_token", header.substring(6));
        //             }
        //             if ("user".equals(role)) {
        //                 //拿到"user_token"头信息，表示当前角色是user
        //                 request.setAttribute("user_token", header.substring(6));
        //             }
        //         } catch (Exception e) {
        //             throw new RuntimeException("令牌不正确");
        //         }
        //     }
        // }
        //所有请求都通过，具体权限在service层判断
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
