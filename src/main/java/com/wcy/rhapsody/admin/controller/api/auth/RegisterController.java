package com.wcy.rhapsody.admin.controller.api.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.dto.UserRegisterDTO;
import com.wcy.rhapsody.admin.modules.entity.User;
import com.wcy.rhapsody.admin.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册
 *
 * @author Yeeep 2020/11/12
 */
@Api(tags = "用户注册控制器")
@RestController
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param dto
     * @return
     */
    @PostMapping("/auth/register")
    public R register(@RequestBody UserRegisterDTO dto) {
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
