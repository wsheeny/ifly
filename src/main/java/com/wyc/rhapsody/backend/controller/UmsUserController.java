package com.wyc.rhapsody.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.rhapsody.backend.common.api.ApiResult;
import com.wyc.rhapsody.backend.config.redis.RedisService;
import com.wyc.rhapsody.backend.model.dto.ActiveDTO;
import com.wyc.rhapsody.backend.model.dto.LoginDTO;
import com.wyc.rhapsody.backend.model.dto.RegisterDTO;
import com.wyc.rhapsody.backend.model.entity.TbPost;
import com.wyc.rhapsody.backend.model.entity.ums.UmsUser;
import com.wyc.rhapsody.backend.service.IPostService;
import com.wyc.rhapsody.backend.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户控制器
 *
 * @author Knox 2020/11/7
 */
@RestController
@RequestMapping("/ums/user")
@Api(tags = "UmsUserController", description = "账号处理器")
public class UmsUserController extends BaseController {

    @Resource
    private IUmsUserService iUmsUserService;

    @Resource
    private IPostService iPostService;

    @Resource
    private RedisService redisService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult<Map<String, Object>> register(@Valid @RequestBody RegisterDTO dto) {
        UmsUser user = iUmsUserService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map);
    }

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        String token = iUmsUserService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("账号密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ApiResult.success(map, "登录成功");
    }

    @ApiOperation(value = "获取登录数据")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ApiResult<UmsUser> getUser(Principal principal) {
        UmsUser user = iUmsUserService.getUserByUsername(principal.getName());
        return ApiResult.success(user);
    }

    @PostMapping("/logout")
    public ApiResult<String> logout() {
        SecurityContextHolder.clearContext();
        return ApiResult.success("注销成功");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改账户资料")
    public ApiResult<UmsUser> updateUser(@RequestBody UmsUser umsUser) {
        iUmsUserService.updateById(umsUser);
        return ApiResult.success(umsUser);
    }

    @ApiOperation(value = "根据用户名查询", notes = "username访问用户主页")
    @ApiImplicitParam(name = "username", value = "username用户名", required = true, paramType = "path")
    @GetMapping("/{username}")
    public ApiResult getUserByName(@PathVariable("username") String username,
                                   @ApiParam(value = "pageNo", name = "页码") @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>(16);
        UmsUser user = iUmsUserService.getUserByUsername(username);
        Assert.notNull(user, "用户不存在");
        Page<TbPost> page = iPostService.page(new Page<>(pageNo, size),
                new LambdaQueryWrapper<TbPost>().eq(TbPost::getUserId, user.getId()));
        map.put("user", user);
        map.put("topics", page);
        return ApiResult.success(map);
    }

    @PostMapping("/active")
    public ApiResult<Object> active(@RequestBody @Valid ActiveDTO activeDTO) {
        UmsUser user = iUmsUserService.getUserByUsername(activeDTO.getUser());
        Assert.notNull(user, "用户不存在");
        Assert.isTrue(!user.getActive(), "账号已激活");

        String activeCode = (String) redisService.get("activeCode[" + activeDTO.getUser() + "]");
        Assert.isTrue(activeCode.equals(activeDTO.getCode()), "激活码错误");

        user.setActive(true);
        boolean b = iUmsUserService.updateById(user);
        if (b) {
            redisService.del("activeCode[" + activeDTO.getUser() + "]");
            return ApiResult.success("恭喜你，账号激活成功!");
        }
        return ApiResult.failed("账号激活失败！");
    }
}
