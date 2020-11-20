package com.wcy.rhapsody.admin.controller.api.auth;

import cn.hutool.core.util.IdUtil;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.dto.UserLoginDTO;
import com.wcy.rhapsody.admin.modules.entity.User;
import com.wcy.rhapsody.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 登录控制器
 *
 * @author Yeeep 2020/11/7
 */
@Slf4j
@Api(tags = "登录控制器")
@RestController
@RequestMapping("/auth")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 前台登录
     *
     * @param loginDTO 登录请求对象
     * @return
     */
    @ApiOperation(value = "前台登录", notes = "前台用户登录接口", httpMethod = "POST")
    @ApiImplicitParam(value = "登录信息")
    @PostMapping("/login")
    public R login(@RequestBody UserLoginDTO loginDTO) {
        Assert.notNull(loginDTO, "参数补全，请补全后再次登录");
        String username = loginDTO.getUsername();
        Assert.hasText(username, "请输入您的登录账号");
        String password = loginDTO.getPassword();
        Assert.hasText(password, "请输入您的登录密码");
        try {
            Subject subject = getSubject();
            Boolean rememberMe = loginDTO.getRememberMe();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password, rememberMe);
            subject.login(usernamePasswordToken);

            User user = (User) getSubject().getPrincipal();
            String token = IdUtil.fastSimpleUUID();
            user.setToken(token);
            userService.updateById(user);


            return R.ok().data(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            log.error("账号密码不匹配，请重新登录");
        }
        return R.error().code(401).message("账号密码不匹配，请校验后再次登录");
    }

}
