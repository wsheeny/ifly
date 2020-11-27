// package com.wcy.rhapsody.admin.component;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import java.util.ArrayList;
// import java.util.List;
//
// /**
//  * @author Yeeep
//  * @date 2020/11/27
//  */
// @Configuration
// public class FilterConfig {
//
//     @Autowired
//     private JwtAuthenticationTokenFilter tokenFilter;
//
//     @Bean
//     public FilterRegistrationBean<JwtAuthenticationTokenFilter> registrationBean() {
//         FilterRegistrationBean<JwtAuthenticationTokenFilter> registrationBean = new FilterRegistrationBean<>();
//
//         registrationBean.setFilter(tokenFilter);
//         registrationBean.setName("tokenFilter");
//
//         List<String> list = new ArrayList<>();
//         list.add("/topic/post");
//         list.add("/topic/update");
//         list.add("/topic/delete/*");
//         list.add("/follow/do/**");
//         list.add("/follow/undo/**");
//         registrationBean.setUrlPatterns(list);
//         // registrationBean.addUrlPatterns("/topic/post");
//         registrationBean.setOrder(1);
//         return registrationBean;
//     }
//
//     // 多个
//
// }
