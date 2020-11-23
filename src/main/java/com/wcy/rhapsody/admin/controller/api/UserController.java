package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.entity.web.User;
import com.wcy.rhapsody.admin.modules.vo.ProfileVO;
import com.wcy.rhapsody.admin.service.api.TopicService;
import com.wcy.rhapsody.admin.service.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * 账户控制器
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "账户控制器")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

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
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = userService.getOne(lambdaQueryWrapper);
        Assert.notNull(user, "用户不存在");
        return R.ok().data(user);
    }

    /**
     * 登录后跳转首页获取用户信息
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "登录后，获取用户信息", notes = "登录成功，请求用户信息，需要携带token", httpMethod = "POST")
    @PostMapping("/info")
    public R getUserInfoByToken(@ApiParam(name = "token", value = "用户登录Token", required = true)
                                @RequestParam String token) {
        Assert.notNull(token, "请检查参数是否正确");
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getToken, token);
        User one = userService.getOne(wrapper);
        Assert.notNull(one, "用户不存在");
        return R.ok().data(one);
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
