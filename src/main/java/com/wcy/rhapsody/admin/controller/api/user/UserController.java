package com.wcy.rhapsody.admin.controller.api.user;

import com.wcy.rhapsody.admin.config.jwt.JwtTokenUtil;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.entity.web.User;
import com.wcy.rhapsody.admin.modules.vo.ProfileVO;
import com.wcy.rhapsody.admin.service.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 账户控制器
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "账户控制器")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 用户主页：根据用户名查询
     *
     * @param username
     * @return
     */
    @ApiOperation(value = "根据用户名查询", notes = "username访问用户主页")
    @ApiImplicitParam(name = "username", value = "username用户名", required = true, paramType = "path")
    @GetMapping("/{username}")
    public R getUserByName(@PathVariable("username") String username) {
        Assert.hasText(username, "参数补全，请补全后再查");
        User user = userService.selectByUsername(username);
        Assert.notNull(user, "用户不存在");
        return R.ok().data(user);
    }

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    @GetMapping("/id/{id}")
    @ApiOperation(value = "获取用户信息", notes = "")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    public R getProfile(@PathVariable("id") String id) {
        ProfileVO profile = userService.getUserProfile(id);

        return R.ok().data(profile);
    }

}
