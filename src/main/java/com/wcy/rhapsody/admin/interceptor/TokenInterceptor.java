// package com.wcy.rhapsody.admin.interceptor;
//
// import com.wcy.rhapsody.admin.config.jwt.JwtTokenUtil;
// import com.wcy.rhapsody.admin.modules.entity.web.User;
// import com.wcy.rhapsody.admin.service.api.UserService;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;
// import org.springframework.util.StringUtils;
// import org.springframework.web.method.HandlerMethod;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;
//
// import javax.annotation.Resource;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
//
// /**
//  * JWT TOKEN拦截器
//  *
//  * @author Yeeep 2020/11/23
//  */
// @Component
// public class TokenInterceptor implements HandlerInterceptor {
//
//     private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);
//
//     public static final String TOKEN = "X-Token";
//
//     @Resource
//     private JwtTokenUtil jwtTokenUtil;
//
//     @Resource
//     private UserService userService;
//
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         HttpSession session = request.getSession();
//
//         if (handler instanceof HandlerMethod) {
//             String token = request.getHeader(TOKEN);
//             if (StringUtils.isEmpty(token)) {
//                 session.setAttribute("isLogin", false);
//                 session.setAttribute("user", null);
//             } else {
//                 // jwtTokenUtil.parseToken(token);
//                 // String username = jwtTokenUtil.parseToken(token);
//                 // User user = userService.selectByUsername(username);
//                 // if (jwtTokenUtil.validateToken(token, user)) {
//                 //     session.setAttribute("isLogin", true);
//                 //     session.setAttribute("user", null);
//                 // }
//                 return true;
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
