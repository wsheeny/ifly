package com.wyc.elegant.admin.controller.auth;

import com.wyc.elegant.admin.common.MyHttpCode;
import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.config.jwt.JwtTokenUtil;
import com.wyc.elegant.admin.controller.BaseController;
import com.wyc.elegant.admin.model.dto.LoginDTO;
import com.wyc.elegant.admin.model.entity.TbUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author Knox 2020/11/7
 */
@Slf4j
@Api(tags = "登录控制器")
@RestController
@RequestMapping("/auth")
public class LoginController extends BaseController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /**
     * 前台登录
     *
     * @param dto 登录请求对象
     * @return
     */
    @ApiOperation(value = "前台登录", notes = "前台用户登录接口", httpMethod = "POST")
    @PostMapping("/login")
    public R login(@Valid @RequestBody LoginDTO dto) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(dto.getUsername(), dto.getPassword(), dto.getRememberMe());
            subject.login(usernamePasswordToken);

            String token = jwtTokenUtil.generateToken(getLoginProfile());
            Map<String, Object> map = new HashMap<>(16);
            map.put("token", token);
            return R.ok().message("登录成功").data(map);
        } catch (AuthenticationException e) {
            log.error("---------------> 用户`{}`登录错误", dto.getUsername());
            return R.error().code(MyHttpCode.USER_NAME_PASS_ERROR).message("用户名或密码错误");
        }
    }

    /**
     * 登录后跳转首页获取用户信息
     *
     * @return
     */
    @ApiOperation(value = "登录后，获取用户信息", notes = "登录成功，请求用户信息，需要携带token", httpMethod = "POST")
    @GetMapping("/user/info")
    public R getUserInfoByToken() {
        //获取shiro中登录信息，也可以查询数据库获取最新数据
        TbUser user = getLoginProfile();
        return R.ok().data(user);
    }
}
