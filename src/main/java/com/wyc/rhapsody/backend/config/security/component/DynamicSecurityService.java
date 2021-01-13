package com.wyc.rhapsody.backend.config.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * 动态权限相关业务类
 *
 * @author knox
 * @since 2020/2/7
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     *
     * @return 资源集合
     */
    Map<String, ConfigAttribute> loadDataSource();
}
