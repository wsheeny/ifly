package com.wyc.rhapsody.backend.controller;

import com.wyc.rhapsody.backend.common.api.ApiResult;
import com.wyc.rhapsody.backend.model.entity.TbTip;
import com.wyc.rhapsody.backend.service.TipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Tips控制器
 *
 * @author Knox 2020/11/7
 */
@Api(tags = "tips控制器")
@RestController
@RequestMapping("/api/tip")
public class TipController extends BaseController {

    @Autowired
    private TipService tipService;

    /**
     * 每日随机赠言，目前使用缓存
     *
     * @param type 类型
     * @return
     */
    @ApiOperation(value = "获取每日赠言", notes = "Type默认为1，一般情况使用默认值", httpMethod = "GET")
    @GetMapping("/today")
    public ApiResult<TbTip> getRandomTip(@ApiParam("type类型，默认使用1") @RequestParam(value = "type", defaultValue = "1") Integer type) {
        TbTip tip = tipService.getRandomTip(type);
        return ApiResult.success(tip);
    }
}
