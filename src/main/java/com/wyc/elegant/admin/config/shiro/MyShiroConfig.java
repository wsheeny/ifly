package com.wyc.elegant.admin.config.shiro;

import cn.hutool.crypto.digest.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro安全配置
 *
 * @author Knox
 * @date 2020/12/4
 */
@Slf4j
@Configuration
public class MyShiroConfig {

    @Bean
    MyShiroRealm myShiroRealm() {
        MyShiroRealm realm = new MyShiroRealm();
        // 配置自定义加密方式
        realm.setCredentialsMatcher((token, info) -> {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            // 前台
            String rawPassword = String.valueOf(usernamePasswordToken.getPassword());
            // 数据库
            String encodedPassword = String.valueOf(info.getCredentials());
            return BCrypt.checkpw(rawPassword, encodedPassword);
        });
        return realm;
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm());
        manager.setRememberMeManager(rememberMeManager());
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 安全管理器
        bean.setSecurityManager(securityManager());

        /* 自定义filter注册 */
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwtFilter", new JwtFilter());
        bean.setFilters(filters);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/auth/**", "anon");

        // 所有请求给这个拦截器处理
        // map.put("/**", "jwtFilter");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间 ,单位秒;-->
        simpleCookie.setMaxAge(Cookie.ONE_YEAR);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey("x5+126q47832#4*k".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制指定注解的底层实现使用 cglib 方案
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}