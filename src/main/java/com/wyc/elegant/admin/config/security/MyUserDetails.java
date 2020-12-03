package com.wyc.elegant.admin.config.security;

import com.wyc.elegant.admin.model.entity.TbPermission;
import com.wyc.elegant.admin.model.entity.TbUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 *
 * @author Knox
 * @date 2020/12/3
 */
public class MyUserDetails implements UserDetails {

    private final TbUser tbUser;
    private final List<TbPermission> permissionList;

    public MyUserDetails(TbUser tbUser, List<TbPermission> permissionList) {
        this.tbUser = tbUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getName() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return tbUser.getPassword();
    }

    @Override
    public String getUsername() {
        return tbUser.getUsername();
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
        return tbUser.getStatus();
    }
}
