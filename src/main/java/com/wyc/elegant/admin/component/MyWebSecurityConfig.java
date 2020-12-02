package com.wyc.elegant.admin.component;

import com.wyc.elegant.admin.common.RestAuthenticationEntryPoint;
import com.wyc.elegant.admin.common.RestfulAccessDeniedHandler;
import com.wyc.elegant.admin.model.dto.AdminUserDetails;
import com.wyc.elegant.admin.model.entity.AdminUser;
import com.wyc.elegant.admin.model.entity.Permission;
import com.wyc.elegant.admin.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * spring security配置
 *
 * @author Yeeep
 * @date 2020/12/3
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 当访问接口没有权限时，自定义的返回结果
     */
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    /**
     * 当未登录或者token失效访问接口时，自定义的返回结果
     */
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * spring security自身的登录验证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 内存指定
        auth.inMemoryAuthentication()
                .withUser("root").password("123").roles("ADMIN", "DBA")
                .and()
                .withUser("admin").password("123").roles("ADMIN", "USER")
                .and()
                .withUser("cc").password("123").roles("USER");
        // 动态
        // auth.userDetailsService(userDetailsService())
        //         .passwordEncoder(passwordEncoder());

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            AdminUser admin = adminUserService.getAdminUserByUsername(username);
            if (admin != null) {
                List<Permission> permissionList = adminUserService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin, permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    /**
     * 路径拦截的登录认证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()// 由于使用的是JWT，禁用CSRF保护
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // .and()
        http.authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                ).permitAll()
                // 前端登录
                .antMatchers("/auth/login").permitAll()
                // .antMatchers("/auth/*").permitAll()
                //调用api不需要拦截
                .antMatchers("/api/**").permitAll()
                // 对后台登录注册要允许匿名访问
                .antMatchers("/admin/login", "/admin/register").permitAll()
                //跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS).permitAll();
        // // 全部放行
        // .antMatchers("/**")
        // .permitAll();

        // 除上面外的所有请求全部需要鉴权认证
        // .anyRequest()
        // .authenticated();
        // 禁用缓存
        http.headers().cacheControl();
        // // 添加JWT filter
        // http.addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // //添加自定义未授权和未登录结果返回
        // http.exceptionHandling()
        //         .accessDeniedHandler(restfulAccessDeniedHandler)
        //         .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}