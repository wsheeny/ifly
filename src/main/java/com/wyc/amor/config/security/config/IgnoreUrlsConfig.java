package com.wyc.amor.config.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于配置白名单资源路径
 * <p>
 * 支持但服务扩展
 *
 * @author knox
 * @date 2020/11/5
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
@Component
public class IgnoreUrlsConfig {

    /**
     * 白名单
     */
    private List<String> urls = new ArrayList<>();
}
