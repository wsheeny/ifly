package com.wcy.rhapsody.admin.config.shiro.realm;

import cn.hutool.crypto.digest.BCrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * BCrypt密码匹配
 *
 * @author Yeeep
 */
public class MyCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String rawPassword = String.valueOf((char[]) token.getCredentials());
        String encodedPassword = String.valueOf(info.getCredentials());
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
