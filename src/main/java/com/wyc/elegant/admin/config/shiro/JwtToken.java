package com.wyc.elegant.admin.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Knox
 * @date 2020/12/5
 */
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}