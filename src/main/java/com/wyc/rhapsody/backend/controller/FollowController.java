package com.wyc.rhapsody.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyc.rhapsody.backend.common.api.ApiResult;
import com.wyc.rhapsody.backend.common.exception.Asserts;
import com.wyc.rhapsody.backend.model.entity.TbFollow;
import com.wyc.rhapsody.backend.model.entity.ums.UmsUser;
import com.wyc.rhapsody.backend.service.FollowService;
import com.wyc.rhapsody.backend.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    private IUmsUserService IUmsUserService;

    /**
     * 关注
     *
     * @param parentId
     * @return
     */
    @GetMapping("/do/{userId}")
    public ApiResult handleFollow(@PathVariable("userId") String parentId) {
        UmsUser umsUser = null;
        if (parentId.equals(umsUser.getId())) {
            Asserts.fail("您脸皮太厚了，怎么可以关注自己呢😮");
        }
        TbFollow one = followService.getOne(
                new LambdaQueryWrapper<TbFollow>()
                        .eq(TbFollow::getParentId, parentId)
                        .eq(TbFollow::getFollowerId, umsUser.getId()));
        if (!ObjectUtils.isEmpty(one)) {
            Asserts.fail("已关注");
        }

        TbFollow follow = new TbFollow();
        follow.setParentId(parentId);
        follow.setFollowerId(umsUser.getId());
        followService.save(follow);
        return ApiResult.success("关注成功");
    }

    /**
     * 取消关注
     *
     * @param parentId
     * @return
     */
    @GetMapping("/undo/{userId}")
    public ApiResult handleUnFollow(@PathVariable("userId") String parentId) {
        UmsUser umsUser = null;
        TbFollow one = followService.getOne(
                new LambdaQueryWrapper<TbFollow>()
                        .eq(TbFollow::getParentId, parentId)
                        .eq(TbFollow::getFollowerId, umsUser.getId()));
        if (ObjectUtils.isEmpty(one)) {
            Asserts.fail("未关注！");
        }

        followService.remove(new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getParentId, parentId)
                .eq(TbFollow::getFollowerId, umsUser.getId()));

        return ApiResult.success("取关成功");
    }

    /**
     * 验证是否关注
     */
    @ApiOperation(value = "验证是否关注", notes = "")
    @ApiImplicitParam(value = "topicUserId", name = "当前浏览话题作者ID", required = true, paramType = "path")
    @GetMapping("/validate/{topicUserId}")
    public ApiResult isFollow(@PathVariable("topicUserId") String topicUserId) {
        UmsUser umsUser = null;
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if (!ObjectUtils.isEmpty(umsUser)) {
            TbFollow one = followService.getOne(new LambdaQueryWrapper<TbFollow>()
                    .eq(TbFollow::getParentId, topicUserId)
                    .eq(TbFollow::getFollowerId, umsUser.getId()));
            if (!ObjectUtils.isEmpty(one)) {
                map.put("hasFollow", true);
            }
        }
        return ApiResult.success(map);
    }

    /**
     * 我的粉丝列表
     *
     * @return
     */
    @GetMapping("/myfans")
    @ApiOperation(value = "获取我的分类列表", notes = "")
    public ApiResult followerList(@ApiParam(value = "username", name = "用户名", required = true)
                                  @RequestParam("username") String username) {

        UmsUser user = IUmsUserService.getOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, username));
        Assert.notNull(user, "用户不存在");

        List<TbFollow> list = followService.list(new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getParentId, user.getId()));

        return ApiResult.success(list);
    }

    /**
     * 我的关注列表
     *
     * @return
     */
    @GetMapping("/{username}/myfollow")
    @ApiOperation(value = "获取我的关注列表", notes = "")
    @ApiImplicitParam(name = "username", value = "用户username", required = true, paramType = "path")
    public ApiResult followList(@PathVariable("username") String username) {
        UmsUser user = IUmsUserService.getUserByUsername(username);
        Assert.notNull(user, "用户不存在");

        List<TbFollow> list = followService.list(new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getFollowerId, user.getId()));

        return ApiResult.success(list);
    }
}
