package com.wcy.rhapsody.admin.controller.api.auth;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wcy.rhapsody.admin.config.jwt.JwtTokenUtil;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.dto.LoginDTO;
import com.wcy.rhapsody.admin.modules.entity.web.User;
import com.wcy.rhapsody.admin.service.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

/**
 * 登录控制器
 *
 * @author Yeeep 2020/11/7
 */
@Slf4j
@Api(tags = "登录控制器")
@RestController
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 前台登录
     *
     * @param dto 登录请求对象
     * @return
     */
    @ApiOperation(value = "前台登录", notes = "前台用户登录接口", httpMethod = "POST")
    @ApiImplicitParam(value = "登录信息")
    @PostMapping("/login")
    public R login(@Valid @RequestBody LoginDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.error().message(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        String username = dto.getUsername();
        String password = dto.getPassword();
        // 验证数据库用户
        User dbUser = userService.getOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, username));
        Assert.notNull(dbUser, "用户名密码错误");
        // 验证密码
        boolean checkpw = BCrypt.checkpw(password, dbUser.getPassword());
        if (checkpw) {
            String token = jwtTokenUtil.generateToken(dbUser);
            dbUser.setToken(token);
            userService.updateById(dbUser);
            return R.ok().data(token);
        }
        return R.error().message("用户名密码错误");
    }

}
