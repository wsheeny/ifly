package com.wyc.elegant.admin.model.dto;

import com.wyc.elegant.admin.model.entity.AdminUser;
import com.wyc.elegant.admin.model.entity.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 *
 * @author Yeeep
 * @date 2020/12/3
 */
public class AdminUserDetails implements UserDetails {

    private final AdminUser adminUser;
    private final List<Permission> permissionList;

    public AdminUserDetails(AdminUser adminUser, List<Permission> permissionList) {
        this.adminUser = adminUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        // return permissionList.stream()
        //         .filter(permission -> permission.getValue() != null)
        //         .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
        //         .collect(Collectors.toList());
        return null;
    }

    @Override
    public String getPassword() {
        return adminUser.getPassword();
    }

    @Override
    public String getUsername() {
        return adminUser.getUsername();
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

    /**
     * 账号状态
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return adminUser.getStatus();
        // return adminUser.getStatus().equals(1);
    }
}
