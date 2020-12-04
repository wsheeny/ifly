// package com.wyc.elegant.admin.config.jwt;
//
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.util.ObjectUtils;
// import org.springframework.web.filter.OncePerRequestFilter;
//
// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
//
// /**
//  * JWT登录授权过滤器
//  *
//  * @author Knox
//  * @date 2020/12/3
//  */
// @Slf4j
// @Component
// public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//     @Autowired
//     private JwtTokenUtil tokenUtil;
//
//     @Autowired
//     private JwtProperties jwtProperties;
//
//     public static final String OPTIONS = "OPTIONS";
//
//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain chain) throws ServletException, IOException {
//         // 跳过OPTIONS
//         if (OPTIONS.equals(request.getMethod())) {
//             response.setStatus(HttpServletResponse.SC_OK);
//         } else {
//             String authHeader = request.getHeader(jwtProperties.getTokenHeader());
//             if (!ObjectUtils.isEmpty(authHeader) && authHeader.startsWith(jwtProperties.getTokenHead())) {
//                 // The part after "Bearer "
//                 String authToken = authHeader.substring(jwtProperties.getTokenHead().length());
//                 String username = tokenUtil.getUserNameFromToken(authToken);
//                 log.info("checking username:{}", username);
//                 // shiro不为空，后台已登录
//                 if (username != null) {
//                     // TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
//                     // if (tokenUtil.validateToken(authToken, principal)) {
//                     //     log.info("authenticated user:{}", username);
//                     // }
//                 }
//             }
//         }
//         chain.doFilter(request, response);
//     }
// }