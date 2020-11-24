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
import io.swagger.annotations.ApiParam;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "登录控制器")
@RestController
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
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
    public R login(@Valid @RequestBody LoginDTO dto) {
        Map<String, Object> map = new HashMap<>(16);
        String username = dto.getUsername();
        String password = dto.getPassword();
        // 验证数据库用户
        User dbUser = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        Assert.notNull(dbUser, "用户名密码错误");
        // 验证密码
        boolean checkPw = BCrypt.checkpw(password, dbUser.getPassword());
        if (checkPw) {
            String token = jwtTokenUtil.generateToken(dbUser);
            dbUser.setToken(token);
            userService.updateById(dbUser);

            map.put("token", token);
            map.put("user", dbUser);
            return R.ok().data(map);
        }
        return R.error().message("用户名密码错误");
    }

    /**
     * 登录后跳转首页获取用户信息
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "登录后，获取用户信息", notes = "登录成功，请求用户信息，需要携带token", httpMethod = "POST")
    @PostMapping("/login/profile")
    public R getUserInfoByToken(@ApiParam(name = "token", value = "用户登录Token", required = true)
                                @RequestParam String token) {
        String username = jwtTokenUtil.parseToken(token).getSubject();
        User user = userService.selectByUsername(username);
        Assert.notNull(user, "用户不存在");
        return R.ok().data(user);
    }
}
