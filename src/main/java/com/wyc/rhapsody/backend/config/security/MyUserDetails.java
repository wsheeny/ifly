package com.wyc.rhapsody.backend.config.security;

import com.wyc.rhapsody.backend.model.entity.TbUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * SpringSecurity定义用于封装用户信息的类（主要是用户信息和权限），需要自行实现；
 *
 * @author Knox
 * @date 2020/12/5
 */
@Data
public class MyUserDetails implements UserDetails {

    private final TbUser user;

    public MyUserDetails(TbUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus();
    }
}
