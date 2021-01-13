package com.wyc.amor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyc.amor.common.api.ApiResult;
import com.wyc.amor.common.exception.ApiAsserts;
import com.wyc.amor.model.entity.TbFollow;
import com.wyc.amor.model.entity.ums.UmsUser;
import com.wyc.amor.service.IBmsFollowService;
import com.wyc.amor.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户关系
 *
 * @author Knox 2020/11/20
 */
@RestController
@RequestMapping("/relationship")
@Api(tags = "BmsRelationshipController", description = "用户关系处理")
public class BmsRelationshipController extends BaseController {

    @Resource
    private IBmsFollowService bmsFollowService;

    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/subscribe/{userId}")
    @ApiOperation(value = "关注")
    public ApiResult<Object> handleFollow(@PathVariable("userId") String parentId, Principal principal) {
        UmsUser umsUser = umsUserService.getUserByUsername(principal.getName());
        if (parentId.equals(umsUser.getId())) {
            ApiAsserts.fail("您脸皮太厚了，怎么可以关注自己呢😮");
        }
        TbFollow one = bmsFollowService.getOne(
                new LambdaQueryWrapper<TbFollow>()
                        .eq(TbFollow::getParentId, parentId)
                        .eq(TbFollow::getFollowerId, umsUser.getId()));
        if (!ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("已关注");
        }

        TbFollow follow = new TbFollow();
        follow.setParentId(parentId);
        follow.setFollowerId(umsUser.getId());
        bmsFollowService.save(follow);
        return ApiResult.success("关注成功");
    }

    @GetMapping("/unsubscribe/{userId}")
    @ApiOperation(value = "取消关注")
    public ApiResult<Object> handleUnFollow(@PathVariable("userId") String parentId, Principal principal) {
        UmsUser umsUser = umsUserService.getUserByUsername(principal.getName());
        TbFollow one = bmsFollowService.getOne(
                new LambdaQueryWrapper<TbFollow>()
                        .eq(TbFollow::getParentId, parentId)
                        .eq(TbFollow::getFollowerId, umsUser.getId()));
        if (ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("未关注！");
        }
        bmsFollowService.remove(new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getParentId, parentId)
                .eq(TbFollow::getFollowerId, umsUser.getId()));
        return ApiResult.success("取关成功");
    }

    @GetMapping("/fans")
    @ApiOperation(value = "获取我的粉丝")
    public ApiResult<List<TbFollow>> followerList(@ApiParam(value = "username", name = "用户名", required = true)
                                                  @RequestParam("username") String username) {
        UmsUser user = umsUserService.getOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, username));
        if (ObjectUtils.isEmpty(user)) {
            ApiAsserts.fail("用户不存在");
        }
        List<TbFollow> list = bmsFollowService.list(new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getParentId, user.getId()));
        return ApiResult.success(list);
    }

    @GetMapping("/followers")
    @ApiOperation(value = "获取我的关注")
    public ApiResult<List<TbFollow>> followList(@ApiParam(value = "username", name = "用户名", required = true)
                                                @RequestParam("username") String username) {
        UmsUser user = umsUserService.getUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            ApiAsserts.fail("用户不存在");
        }
        List<TbFollow> list = bmsFollowService.list(new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getFollowerId, user.getId()));
        return ApiResult.success(list);
    }

    @GetMapping("/validate/{topicUserId}")
    @ApiOperation(value = "验证是否关注")
    @ApiImplicitParam(value = "topicUserId", name = "当前浏览话题作者ID", required = true, paramType = "path")
    public ApiResult<Map<String, Object>> isFollow(@PathVariable("topicUserId") String topicUserId, Principal principal) {
        UmsUser umsUser = umsUserService.getUserByUsername(principal.getName());
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if (!ObjectUtils.isEmpty(umsUser)) {
            TbFollow one = bmsFollowService.getOne(new LambdaQueryWrapper<TbFollow>()
                    .eq(TbFollow::getParentId, topicUserId)
                    .eq(TbFollow::getFollowerId, umsUser.getId()));
            if (!ObjectUtils.isEmpty(one)) {
                map.put("hasFollow", true);
            }
        }
        return ApiResult.success(map);
    }
}
