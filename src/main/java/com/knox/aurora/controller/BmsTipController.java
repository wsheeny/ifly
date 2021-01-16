package com.knox.aurora.controller;

import com.knox.aurora.common.api.ApiResult;
import com.knox.aurora.model.entity.BmsTip;
import com.knox.aurora.service.IBmsTipService;
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
    public ApiResult<BmsTip> getRandomTip(@ApiParam("type类型，默认使用1") @RequestParam(value = "type", defaultValue = "1") Integer type) {
        BmsTip tip = bmsTipService.getRandomTip(type);
        return ApiResult.success(tip);
    }
}
