// package com.wyc.elegant.admin.component;
//
// import com.wyc.elegant.admin.annotation.RequireLogin;
// import com.wyc.elegant.admin.utils.JwtTokenUtil;
// import com.wyc.elegant.admin.exception.TokenException;
// import com.wyc.elegant.admin.model.entity.User;
// import com.wyc.elegant.admin.service.UserService;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.util.StringUtils;
// import org.springframework.web.method.HandlerMethod;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import java.lang.reflect.Method;
//
// /**
//  * JWT TOKEN拦截器
//  *
//  * @author Yeeep 2020/11/23
//  */
// @Component
// public class TokenInterceptor implements HandlerInterceptor {
//
//     private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
//
//     public static final String TOKEN = "X-Token";
//
//     @Autowired
//     private JwtTokenUtil jwtTokenUtil;
//
//     @Autowired
//     private UserService userService;
//
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         HttpSession session = request.getSession();
//         String uri = request.getRequestURI();
//         String token = request.getHeader(TOKEN);
//         // 是映射到方法
//         if (handler instanceof HandlerMethod) {
//             HandlerMethod handlerMethod = (HandlerMethod) handler;
//             Method method = handlerMethod.getMethod();
//             // 检查有没有需要用户权限的注解
//             if (method.isAnnotationPresent(RequireLogin.class)) {
//                 RequireLogin requireToken = method.getAnnotation(RequireLogin.class);
//                 if (requireToken.required()) {
//                     if (!StringUtils.isEmpty(token)) {
//                         String username = jwtTokenUtil.parseToken(token).getSubject();
//                         if (!StringUtils.isEmpty(username)) {
//                             User user = userService.selectByUsername(username);
//                             if (jwtTokenUtil.validateToken(token, user)) {
//                                 logger.info("用户 {} Token验证成功", username);
//                                 session.setAttribute("loginUser", user);
//                             }
//                         }
//                     } else {
//                         logger.info("用户未登录，已拒绝其请求 {}", uri);
//                         throw new TokenException();
//                     }
//                 }
//             }
//         }
//         return true;
//     }
//
//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//     }
//
//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//     }
// }
