package com.wyc.amor.config.security.config;

import com.wyc.amor.model.entity.ums.UmsUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户登录数据
 *
 * @author knox
 * @since 2021-01-09
 */
public class AdminUserDetails implements UserDetails {

    private final UmsUser umsUser;

    // private final List<UmsResource> resourceList;

    public AdminUserDetails(UmsUser umsUser) {
        this.umsUser = umsUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        // return resourceList.stream()
        //         .map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
        //         .collect(Collectors.toList());
        return null;
    }

    @Override
    public String getPassword() {
        return umsUser.getPassword();
    }

    @Override
    public String getUsername() {
        return umsUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return umsUser.getStatus();
    }
}
