package com.wyc.elegant.admin.controller.auth;

import com.wyc.elegant.admin.common.MyHttpCode;
import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.controller.BaseController;
import com.wyc.elegant.admin.model.dto.LoginDTO;
import com.wyc.elegant.admin.model.entity.TbUser;
import com.wyc.elegant.admin.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author Knox 2020/11/7
 */
@Api(tags = "登录控制器")
@RestController
@RequestMapping("/auth")
public class LoginController extends BaseController {

    @Autowired
    private TbUserService userService;

    /**
     * 前台登录
     *
     * @param dto 登录请求对象
     * @return
     */
    @ApiOperation(value = "前台登录", notes = "前台用户登录接口", httpMethod = "POST")
    @PostMapping("/login")
    public R login(@Valid @RequestBody LoginDTO dto) {
        String token = userService.login(dto);
        if (token != null) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("token", token);
            return R.ok().message("登录成功").data(map);
        }
        return R.error().code(MyHttpCode.USER_NAME_PASS_ERROR).message("用户名或密码错误");
    }

    /**
     * 登录后跳转首页获取用户信息
     *
     * @param principal
     * @return
     */
    @ApiOperation(value = "登录后，获取用户信息", notes = "登录成功，请求用户信息，需要携带token", httpMethod = "POST")
    @PostMapping("/login/profile")
    public R getUserInfoByToken(Principal principal) {
        //details里面可能存放了当前登录用户的详细信息，也可以通过cast后拿到
        TbUser user = userService.getUserByUsername(principal.getName());
        Assert.notNull(user, "用户不存在");
        return R.ok().data(user);
    }
}
