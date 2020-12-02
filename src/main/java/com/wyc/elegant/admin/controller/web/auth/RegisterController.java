package com.wyc.elegant.admin.controller.web.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.controller.BaseController;
import com.wyc.elegant.admin.model.dto.RegisterDTO;
import com.wyc.elegant.admin.model.entity.User;
import com.wyc.elegant.admin.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户注册
 *
 * @author Yeeep 2020/11/12
 */
@Api(tags = "用户注册控制器", value = "RegisterController")
@RestController
@RequestMapping("/auth")
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public R register(@RequestBody @Valid RegisterDTO dto) {
        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getName()));
        Assert.isNull(one, "账号已存在，请更换");

        User one1 = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, dto.getEmail()));
        Assert.isNull(one1, "邮箱已注册，请直接登录");
        // 创建
        userService.createUser(dto);
        return R.ok().message("注册成功");
    }
}
