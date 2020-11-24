package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.exception.MyException;
import com.wcy.rhapsody.admin.modules.entity.web.Follow;
import com.wcy.rhapsody.admin.modules.entity.web.User;
import com.wcy.rhapsody.admin.service.api.FollowService;
import com.wcy.rhapsody.admin.service.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 关注
 *
 * @author Yeeep 2020/11/20
 */
@RestController
@Api(tags = "关注处理器")
public class FollowController extends BaseController {

    @Resource
    private FollowService followService;

    @Resource
    private UserService userService;


    /**
     * 关注
     *
     * @param parentId
     * @return
     */
    @GetMapping("/follow/{userId}")
    public R handleFollow(@PathVariable("userId") String parentId, HttpServletRequest request) {
        HttpSession session = request.getSession();

        Boolean isLogin = (Boolean) session.getAttribute("isLogin");

        if (!isLogin) {
            throw new MyException("未登录");
        }
        User user = (User) session.getAttribute("user");
        Assert.isTrue(!parentId.equals(user.getId()), "您脸皮太厚了，自己怎么可以关注自己呢(⊙o⊙)…");
        Follow one = followService.getOne(
                new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, parentId).eq(Follow::getFollowerId, user.getId()));
        Assert.isNull(one, "您已关注此用户");

        Follow follow = new Follow();
        follow.setParentId(parentId);
        follow.setFollowerId(user.getId());
        followService.save(follow);
        return R.ok().message("关注成功");
    }

    /**
     * 取消关注
     *
     * @param parentId
     * @return
     */
    @GetMapping("/unfollow/{userId}")
    public R handleUnFollow(@PathVariable("userId") String parentId, HttpServletRequest request) {
        Object isLogin = request.getSession().getAttribute("isLogin");
        System.out.println(isLogin);
        User user = (User) getSubject().getPrincipal();
        Assert.notNull(user, "登录后操作");

        Follow one = followService.getOne(
                new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, parentId).eq(Follow::getFollowerId, user.getId()));
        Assert.notNull(one, "当前未关注此用户");

        followService.remove(new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, parentId)
                .eq(Follow::getFollowerId, user.getId()));

        return R.ok().message("取关成功");
    }

    /**
     * 我的粉丝列表
     *
     * @return
     */
    @GetMapping("/{username}/follower")
    @ApiOperation(value = "获取我的分类列表", notes = "")
    @ApiImplicitParam(name = "username", value = "用户username", required = true, paramType = "path")
    public R followerList(@PathVariable("username") String username) {

        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        Assert.notNull(user, "用户不存在");

        List<Follow> list = followService.list(new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, user.getId()));

        return R.ok().data(list);
    }

    /**
     * 我的关注列表
     *
     * @return
     */
    @GetMapping("/{username}/follow")
    @ApiOperation(value = "获取我的关注列表", notes = "")
    @ApiImplicitParam(name = "username", value = "用户username", required = true, paramType = "path")
    public R followList(@PathVariable("username") String username) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        Assert.notNull(user, "用户不存在");

        List<Follow> list = followService.list(new LambdaQueryWrapper<Follow>().eq(Follow::getFollowerId, user.getId()));

        return R.ok().data(list);
    }
}
