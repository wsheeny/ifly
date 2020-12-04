package com.wyc.elegant.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyc.elegant.admin.common.MyHttpCode;
import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.exception.MyException;
import com.wyc.elegant.admin.model.entity.TbFollow;
import com.wyc.elegant.admin.model.entity.TbUser;
import com.wyc.elegant.admin.service.FollowService;
import com.wyc.elegant.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关注
 *
 * @author Knox 2020/11/20
 */
@RestController
@Api(tags = "关注处理器")
@RequestMapping("/api/follow")
public class FollowController extends BaseController {

    @Autowired
    private FollowService followService;

    @Autowired
    private UserService userService;


    /**
     * 关注
     *
     * @param parentId
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/do/{userId}")
    public R handleFollow(@PathVariable("userId") String parentId) {
        TbUser tbUser = getLoginProfile();
        if (parentId.equals(tbUser.getId())) {
            throw new MyException().code(MyHttpCode.HAS_FOLLOW).message("您脸皮太厚了，怎么可以关注自己呢😮");
        }

        TbFollow one = followService.getOne(
                new LambdaQueryWrapper<TbFollow>()
                        .eq(TbFollow::getParentId, parentId)
                        .eq(TbFollow::getFollowerId, tbUser.getId()));
        if (!ObjectUtils.isEmpty(one)) {
            throw new MyException().code(MyHttpCode.HAS_FOLLOW).message("您已关注过了");
        }

        TbFollow follow = new TbFollow();
        follow.setParentId(parentId);
        follow.setFollowerId(tbUser.getId());
        followService.save(follow);
        return R.ok().message("关注成功");
    }

    /**
     * 取消关注
     *
     * @param parentId
     * @return
     */
    @GetMapping("/undo/{userId}")
    public R handleUnFollow(@PathVariable("userId") String parentId) {
        TbUser tbUser = getLoginProfile();
        TbFollow one = followService.getOne(
                new LambdaQueryWrapper<TbFollow>()
                        .eq(TbFollow::getParentId, parentId)
                        .eq(TbFollow::getFollowerId, tbUser.getId()));
        if (ObjectUtils.isEmpty(one)) {
            throw new MyException().code(MyHttpCode.UN_FOLLOW).message("当前用户未关注，无需取关");
        }

        followService.remove(new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getParentId, parentId)
                .eq(TbFollow::getFollowerId, tbUser.getId()));

        return R.ok().message("取关成功");
    }

    /**
     * 验证是否关注
     */
    @ApiOperation(value = "验证是否关注", notes = "")
    @ApiImplicitParam(value = "topicUserId", name = "当前浏览话题作者ID", required = true, paramType = "path")
    @GetMapping("/validate/{topicUserId}")
    public R isFollow(@PathVariable("topicUserId") String topicUserId) {
        TbUser tbUser = getLoginProfile();
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if (!ObjectUtils.isEmpty(tbUser)) {
            TbFollow one = followService.getOne(new LambdaQueryWrapper<TbFollow>()
                    .eq(TbFollow::getParentId, topicUserId)
                    .eq(TbFollow::getFollowerId, tbUser.getId()));
            if (!ObjectUtils.isEmpty(one)) {
                map.put("hasFollow", true);
            }
        }
        return R.ok().data(map);
    }

    /**
     * 我的粉丝列表
     *
     * @return
     */
    @GetMapping("/myfans")
    @ApiOperation(value = "获取我的分类列表", notes = "")
    public R followerList(@ApiParam(value = "username", name = "用户名", required = true)
                          @RequestParam("username") String username) {

        TbUser user = userService.getOne(new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername, username));
        Assert.notNull(user, "用户不存在");

        List<TbFollow> list = followService.list(new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getParentId, user.getId()));

        return R.ok().data(list);
    }

    /**
     * 我的关注列表
     *
     * @return
     */
    @GetMapping("/{username}/myfollow")
    @ApiOperation(value = "获取我的关注列表", notes = "")
    @ApiImplicitParam(name = "username", value = "用户username", required = true, paramType = "path")
    public R followList(@PathVariable("username") String username) {
        TbUser user = userService.getUserByUsername(username);
        Assert.notNull(user, "用户不存在");

        List<TbFollow> list = followService.list(new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getFollowerId, user.getId()));

        return R.ok().data(list);
    }
}
