package com.wyc.amor.controller;

import com.wyc.amor.common.api.ApiResult;
import com.wyc.amor.model.entity.TbTip;
import com.wyc.amor.service.IBmsTipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Tips控制器
 *
 * @author Knox 2020/11/7
 */
@RestController
@RequestMapping("/tip")
@Api(tags = "BmsTipController", description = "tips控制器")
public class BmsTipController extends BaseController {

    @Resource
    private IBmsTipService bmsTipService;

    @GetMapping("/today")
    @ApiOperation(value = "获取每日赠言", notes = "Type默认为1，一般情况使用默认值", httpMethod = "GET")
    public ApiResult<TbTip> getRandomTip(@ApiParam("type类型，默认使用1") @RequestParam(value = "type", defaultValue = "1") Integer type) {
        TbTip tip = bmsTipService.getRandomTip(type);
        return ApiResult.success(tip);
    }
}
