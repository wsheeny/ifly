package com.wcy.rhapsody.admin.interceptor;

import com.wcy.rhapsody.admin.annotation.PassToken;
import com.wcy.rhapsody.admin.annotation.RequireLogin;
import com.wcy.rhapsody.admin.config.jwt.JwtTokenUtil;
import com.wcy.rhapsody.admin.exception.NoAuthException;
import com.wcy.rhapsody.admin.model.entity.web.User;
import com.wcy.rhapsody.admin.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * JWT TOKEN拦截器
 *
 * @author Yeeep 2020/11/23
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    public static final String TOKEN = "X-Token";

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // System.out.println(session.getId());
        String uri = request.getRequestURI();
        logger.info("检测到用户请求： {}", uri);
        String token = request.getHeader(TOKEN);
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(RequireLogin.class)) {
            RequireLogin requireToken = method.getAnnotation(RequireLogin.class);
            if (requireToken.required()) {
                if (!StringUtils.isEmpty(token)) {
                    String username = jwtTokenUtil.parseToken(token).getSubject();
                    if (!StringUtils.isEmpty(username)) {
                        User user = userService.selectByUsername(username);
                        if (jwtTokenUtil.validateToken(token, user)) {
                            logger.info("用户 {} 验证成功", username);
                            // 根据token登录
                            session.setAttribute("loginUser", user);
                        }
                    }
                } else {
                    logger.info("用户未登录，已拒绝其请求 {}", uri);
                    throw new NoAuthException();
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
