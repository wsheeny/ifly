package com.wcy.rhapsody.admin.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2
 * 访问地址：http://ip:port/swagger-ui.html
 *
 * @author Yeeep
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API接口")
                .apiInfo(ApiInfo())
                .select()
                // /*admin不显示*/
                // .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                // /*error不显示*/
                // .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     */
    private ApiInfo ApiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("本文档描述了后台系统接口定义")
                .version("V0.0.1")
                .contact(new Contact("北辰", "http://www.rhapsody.com", "1020317774@qq.com"))
                .build();
    }
}
