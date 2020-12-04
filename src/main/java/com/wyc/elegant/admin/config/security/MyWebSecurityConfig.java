package com.wyc.elegant.admin.config.security;

import com.wyc.elegant.admin.common.RestAuthenticationEntryPoint;
import com.wyc.elegant.admin.common.RestfulAccessDeniedHandler;
import com.wyc.elegant.admin.config.jwt.JwtAuthenticationTokenFilter;
import com.wyc.elegant.admin.model.entity.TbPermission;
import com.wyc.elegant.admin.model.entity.TbUser;
import com.wyc.elegant.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import java.util.List;

/**
 * spring security配置
 *
 * @author Knox
 * @date 2020/12/3
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 全局security注解
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 实现了DetailsService接口，用来做登陆验证
     */
    @Autowired
    private TbUserService tbUserService;
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
     * SpringSecurity定义的用于对密码进行编码及比对的接口，目前使用的是BCryptPasswordEncoder；
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置UserDetailsService及PasswordEncoder
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 配置需要拦截的url路径、jwt过滤器及出异常后的处理器；
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
                        "/static/**"
                ).permitAll()

                // swagger
                .antMatchers("/swagger**/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()

                // 前端登录,注册,注销
                .antMatchers("/auth/**").permitAll()

                // 对于获取token的rest api要允许匿名访问,不需要拦截
                .antMatchers("/api/**").permitAll()

                .antMatchers("/api/topic/create").authenticated()
                .antMatchers("/api/topic/delete/**").authenticated()
                .antMatchers("/api/topic/update").authenticated()

                // 对后台登录注册要允许匿名访问
                .antMatchers("/admin/login", "/admin/register").permitAll()

                // 跨域请求会先进行一次options请求,放行
                .antMatchers(HttpMethod.OPTIONS).permitAll()

                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 退出登录处理器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        // 禁用缓存
        http.headers().cacheControl();

        // 添加JWT filter，开启登录认证流程过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    /**
     * SpringSecurity定义的核心接口，用于根据用户名获取用户信息，需要自行实现；
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        //获取登录用户信息
        return username -> {
            TbUser tbUser = tbUserService.getUserByUsername(username);
            if (tbUser != null) {
                List<TbPermission> permissionList = tbUserService.getPermissionList(tbUser.getId());
                return new MyUserDetails(tbUser, permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    /**
     * token过滤器
     *
     * @return
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}