package com.wcy.rhapsody.admin.controller.api.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.dto.RegisterDTO;
import com.wcy.rhapsody.admin.modules.entity.web.User;
import com.wcy.rhapsody.admin.service.api.UserService;
import io.swagger.annotations.Api;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * 用户注册
 *
 * @author Yeeep 2020/11/12
 */
@Api(tags = "用户注册控制器")
@RestController
public class RegisterController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public R register(@RequestBody @Valid RegisterDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.error().message(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getName()));
        Assert.isNull(one, "账号已存在，请更换");

        User one1 = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, dto.getEmail()));
        Assert.isNull(one1, "邮箱已注册，请直接登录");

        int i = userService.createUser(dto);

        if (1 == i) {
            return R.ok();
        }
        return R.error().message("注册失败");
    }
}
