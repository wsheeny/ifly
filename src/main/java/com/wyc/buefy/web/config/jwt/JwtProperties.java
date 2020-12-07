package com.wyc.buefy.web.config.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * Jwt配置
 *
 * @author Knox
 * @date 2020/12/3
 */
@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:jwt.properties")
public class JwtProperties {

    @NotBlank(message = "请配置tokenHead")
    private String tokenHead;

    @NotBlank(message = "请配置tokenHeader")
    private String tokenHeader;

    @NotBlank(message = "请配置secret")
    private String secret;

    private long expiration;

}
