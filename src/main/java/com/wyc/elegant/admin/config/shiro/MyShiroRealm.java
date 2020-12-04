package com.wyc.elegant.admin.config.shiro;

import com.wyc.elegant.admin.model.entity.TbPermission;
import com.wyc.elegant.admin.model.entity.TbRole;
import com.wyc.elegant.admin.model.entity.TbUser;
import com.wyc.elegant.admin.service.PermissionService;
import com.wyc.elegant.admin.service.RoleService;
import com.wyc.elegant.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义realm
 *
 * @author Knox
 * @date 2020/12/4
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Lazy
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        TbUser user = (TbUser) principalCollection.getPrimaryPrincipal();
        log.info("===============>用户`{}`授权中···", user.getUsername());
        // 访问@RequirePermission注解的url时触发
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        TbUser dbUser = userService.getUserByUsername(user.getUsername());
        // 获得用户的角色，及权限进行绑定
        TbRole role = roleService.getById(dbUser.getRoleId());
        // 其实这里也可以不要权限那个类了，直接用角色这个类来做鉴权，
        // 不过角色包含很多的权限，已经算是大家约定的了，所以下面还是查询权限然后放在AuthorizationInfo里
        simpleAuthorizationInfo.addRole(role.getName());
        // 查询权限
        List<TbPermission> permissions = permissionService.getByRoleId(dbUser.getRoleId());
        // 将权限具体值取出来组装成一个权限String的集合
        List<String> permissionValues = permissions.stream().map(TbPermission::getValue).collect(Collectors.toList());
        // 将权限的String集合添加进AuthorizationInfo里，后面请求鉴权有用
        simpleAuthorizationInfo.addStringPermissions(permissionValues);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录用户名
        String username = (String) authenticationToken.getPrincipal();
        log.info("===============>用户`{}`认证中···", username);

        //查询数据库
        TbUser dbUser = userService.getUserByUsername(username);
        if (ObjectUtils.isEmpty(dbUser)) {
            throw new UnknownAccountException("账号不存在");
        }
        // 这里验证authenticationToken和simpleAuthenticationInfo的信息,存入对象，方便后面获取
        return new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), getName());
    }
}
