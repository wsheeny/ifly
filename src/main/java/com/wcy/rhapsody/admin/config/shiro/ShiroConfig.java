package com.wcy.rhapsody.admin.config.shiro;

import com.wcy.rhapsody.admin.config.shiro.realm.MyCredentialsMatcher;
import com.wcy.rhapsody.admin.config.shiro.realm.MyShiroRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 * <p>
 * 过滤的文件和权限，密码加密的算法，其用注解等相关功能。
 *
 * @author Yeeep
 */
@Configuration
public class ShiroConfig {
    private final Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 1.ShiroFilterFactoryBean
     * <p>
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        log.info("开始配置shiroFilter...");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 安全管理器配置
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
        shiroFilterFactoryBean.setFilters(filterMap);

        // 拦截器
        Map<String, String> map = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断  相关静态资源
        map.put("/static/**", "anon");

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        map.put("/admin/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;

        //<!-- authc:所有url都必须认证通过才可以访问; user: 表示rememberMe后就可以访问 anon:所有url都都可以匿名访问-->
        map.put("/admin/permission/**", "authc");
        map.put("/admin/role/**", "authc");
        map.put("/admin/system/**", "authc");
        map.put("/admin/admin_user/**", "authc");

        map.put("/admin/index", "user");
        map.put("/admin/comment/**", "user");
        map.put("/admin/sensitive_word/**", "user");
        map.put("/admin/tag/**", "user");
        map.put("/admin/topics/**", "user");
        map.put("/admin/user/**", "user");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/login");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }


    /**
     * 2.安全管理器配置
     * <p>
     * 权限管理，配置主要是Realm的管理认证
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(myRealm());
        // 注入缓存管理器：Ehcache或者Redis*
        // securityManager.setCacheManager(ehCacheManager());

        // 注入cookie管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 3.将自己的验证方式加入容器
     */
    @Bean()
    public MyShiroRealm myRealm() {
        MyShiroRealm realm = new MyShiroRealm();
        // 配置自定义加密方式
        realm.setCredentialsMatcher(new MyCredentialsMatcher());
        return realm;
    }

    // ---------------记住我 START

    /**
     * cookie管理对象
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        log.info("shiro配置cookie管理对象:rememberMeManager");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        //cookieRememberMeManager.setCipherKey传入参数为长度16位的byte[]，否则会报Unable to init cipher instance:无法初始化密码实例的错误
        byte[] cipherKey = Base64.encode(("i will be better").getBytes());
        cookieRememberMeManager.setCipherKey(cipherKey);
        return cookieRememberMeManager;
    }

    /**
     * 配置记住我功能
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        log.info("Shiro配置记住我：rememberMe");
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 记住我cookie生效时间 单位秒，系统默认30天，可以手动在后台设置
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);
        return simpleCookie;
    }
    // ---------------记住我 END


    // ---------------shiro注解START

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持，不加入这个注解不生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    // ---------------shiro注解END

    @Bean
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        //对应前端的checkbox的name = rememberMe
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }

    /**
     * 缓存管理对象
     */
    // @Bean
    // public EhCacheManager ehCacheManager() {
    //     EhCacheManager ehCacheManager = new EhCacheManager();
    //     ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
    //     return ehCacheManager;
    // }

}
