package com.wcy.rhapsody.admin.config.shiro.realm;

import com.wcy.rhapsody.admin.modules.entity.web.Permission;
import com.wcy.rhapsody.admin.modules.entity.web.Role;
import com.wcy.rhapsody.admin.modules.entity.web.User;
import com.wcy.rhapsody.admin.service.api.PermissionService;
import com.wcy.rhapsody.admin.service.api.RoleService;
import com.wcy.rhapsody.admin.service.api.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * shiro权限，认证
 *
 * @author Yeeep
 */
public class MyShiroRealm extends AuthorizingRealm {
    private final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 权限配置类
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        log.info("系统正在为用户{}进行授权...", user.getUsername());
        //访问@RequirePermission注解的url时触发
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User adminUser = userService.selectByUsername(user.getUsername());
        //获得用户的角色，及权限进行绑定
        Role role = roleService.getById(adminUser.getRoleId());
        // 其实这里也可以不要权限那个类了，直接用角色这个类来做鉴权，
        // 不过角色包含很多的权限，已经算是大家约定的了，所以下面还是查询权限然后放在AuthorizationInfo里
        simpleAuthorizationInfo.addRole(role.getName());
        // 查询权限
        List<Permission> permissions = permissionService.selectPermissionsByRoleId(adminUser.getRoleId());
        // 将权限具体值取出来组装成一个权限String的集合
        List<String> permissionValues = permissions.stream().map(Permission::getName).collect(Collectors.toList());
        // 将权限的String集合添加进AuthorizationInfo里，后面请求鉴权有用
        simpleAuthorizationInfo.addStringPermissions(permissionValues);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证配置类
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取登录用户名
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        String username = token1.getUsername();
        log.info("用户：{} 正在登录...", username);
        //验证用户名称
        User dbUser = userService.selectByUsername(username);
        // 如果用户不存在，则抛出未知用户的异常
        if (dbUser == null) {
            throw new UnknownAccountException("账号不存在");
        }
        // 这里验证authenticationToken和simpleAuthenticationInfo的信息,存入对象，方便后面获取
        return new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), getName());
    }

}
