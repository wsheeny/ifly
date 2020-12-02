package com.wyc.elegant.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局定制SpringBoot Mvc特性
 *
 * @author Yeeep
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * 跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:9666", "http://127.0.0.1:10000", "http://47.105.186.18")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(3600);
    }

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注入token拦截器
    }

    // /**
    //  * 解决swagger-ui.html 404无法访问的问题
    //  *
    //  * @param registry
    //  */
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     // 解决静态资源无法访问
    //     registry.addResourceHandler("/**")
    //             .addResourceLocations("classpath:/static/");
    //     // 解决swagger无法访问
    //     registry.addResourceHandler("/swagger-ui.html")
    //             .addResourceLocations("classpath:/META-INF/resources/");
    //     // 解决swagger的js文件无法访问
    //     registry.addResourceHandler("/webjars/**")
    //             .addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }

}
