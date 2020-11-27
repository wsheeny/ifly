// package com.wcy.rhapsody.admin.component;
//
// import com.wcy.rhapsody.admin.config.jwt.JwtTokenUtil;
// import com.wcy.rhapsody.admin.modules.entity.web.User;
// import com.wcy.rhapsody.admin.service.api.UserService;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.util.StringUtils;
// import org.springframework.web.filter.OncePerRequestFilter;
//
// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import java.io.IOException;
//
// /**
//  * JWT登录授权过滤器
//  * <p>
//  *
//  * @author Yeeep
//  * @date 2020/11/27
//  */
// @Component
// public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//     private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
//
//     @Autowired
//     private UserService userService;
//     @Autowired
//     private JwtTokenUtil jwtTokenUtil;
//
//     public static final String TOKEN = "X-Token";
//
//     // TODO: 2020/11/27 后期会修改为过滤自定义注解
//
//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         String uri = request.getRequestURI();
//         logger.info("检测到用户请求：{}", uri);
//         HttpSession session = request.getSession();
//         String authHeader = request.getHeader(TOKEN);
//         if (!StringUtils.isEmpty(authHeader)) {
//             String username = jwtTokenUtil.parseToken(authHeader).getSubject();
//             if (!StringUtils.isEmpty(username)) {
//                 logger.info("验证用户:{}", username);
//                 User user = userService.selectByUsername(username);
//                 if (jwtTokenUtil.validateToken(authHeader, user)) {
//                     // 根据token登录
//                     session.setAttribute("loginUser", user);
//                 }
//             }
//         } else {
//             logger.info("用户未登录，已拒绝其请求");
//         }
//         filterChain.doFilter(request, response);
//     }
// }
