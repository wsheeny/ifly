package com.wyc.rhapsody.backend.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger
 * <p>
 * 访问地址：http://ip:port/swagger-ui.html
 *
 * @author Knox
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.wyc.rhapsody.backend.controller"))
                // 排除
                // .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("BuefyAdmin接口文档")
                .description("admin-api")
                .version("1.0")
                .contact(new Contact("王一宸", "", "1020317774@qq.com"))
                .build();
    }

}
