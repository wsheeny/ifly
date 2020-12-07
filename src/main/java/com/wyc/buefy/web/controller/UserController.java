package com.wyc.buefy.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.buefy.web.common.R;
import com.wyc.buefy.web.config.redis.RedisService;
import com.wyc.buefy.web.model.dto.ActiveDTO;
import com.wyc.buefy.web.model.entity.TbTopic;
import com.wyc.buefy.web.model.entity.TbUser;
import com.wyc.buefy.web.service.TopicService;
import com.wyc.buefy.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户控制器
 *
 * @author Knox 2020/11/7
 */
@Api(tags = "用户控制器", value = "UserController")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private RedisService redisService;

    /**
     * 更改用户
     *
     * @param tbUser
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "更改用户")
    public R updateUser(@RequestBody TbUser tbUser) {
        // userService.updateUser(tbUser);
        return R.ok().message("账号更改成功");
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取登录用户信息")
    public R getUser() {
        TbUser user = getMyUserDetails().getUser();
        return R.ok().data(user).message("账号信息获取成功");
    }

    /**
     * 用户主页：根据用户名查询
     *
     * @param username
     * @return
     */
    @ApiOperation(value = "根据用户名查询", notes = "username访问用户主页")
    @ApiImplicitParam(name = "username", value = "username用户名", required = true, paramType = "path")
    @GetMapping("/{username}")
    public R getUserByName(@PathVariable("username") String username,
                           @ApiParam(value = "pageNo", name = "页码") @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>(16);
        TbUser user = userService.getUserByUsername(username);
        Assert.notNull(user, "用户不存在");
        Page<TbTopic> page = topicService.page(new Page<>(pageNo, size),
                new LambdaQueryWrapper<TbTopic>().eq(TbTopic::getUserId, user.getId()));
        map.put("user", user);
        map.put("topics", page);
        return R.ok().data(map);
    }

    /**
     * 账号激活
     * <p>
     * user/active?name=${active.user}&code=${active.code}
     */
    @PostMapping("/active")
    public R active(@RequestBody @Valid ActiveDTO activeDTO) {
        TbUser user = userService.getUserByUsername(activeDTO.getUser());
        Assert.notNull(user, "用户不存在");
        Assert.isTrue(!user.getActive(), "账号已激活");

        String activeCode = (String) redisService.get("activeCode[" + activeDTO.getUser() + "]");
        Assert.isTrue(activeCode.equals(activeDTO.getCode()), "激活码错误");

        user.setActive(true);
        boolean b = userService.updateById(user);
        if (b) {
            redisService.del("activeCode[" + activeDTO.getUser() + "]");
            return R.ok().message("恭喜你，账号激活成功!");
        }
        return R.error().message("恭喜你，账号激活成功!").code(10000);
    }
}
