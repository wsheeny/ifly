package com.wyc.amor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.amor.common.api.ApiResult;
import com.wyc.amor.model.entity.BmsActivity;
import com.wyc.amor.service.IBmsActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 活动
 *
 * @author Knox 2020/11/21
 */
@RestController
@RequestMapping("/activity")
@Api(tags = "BmsActivityController", description = "活动处理器")
public class BmsActivityController extends BaseController {

    @Resource
    private IBmsActivityService bmsActivityService;

    @GetMapping("/all")
    @ApiOperation(value = "获取活动列表", notes = "可分页，根据参数查询")
    public ApiResult<Page<BmsActivity>> list(BmsActivity event,
                                             @ApiParam(name = "page", value = "页码，默认1")
                                            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                             @ApiParam(name = "size", value = "每页查询数量，默认10")
                                            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        Page<BmsActivity> page1 = bmsActivityService.page(
                new Page<>(page, size),
                new LambdaQueryWrapper<>(event).orderByDesc(BmsActivity::getStatus)
                        .orderByAsc(BmsActivity::getTime));
        return ApiResult.success(page1);
    }

}
