package com.wyc.amor.controller;

import com.wyc.amor.common.api.ApiResult;
import com.wyc.amor.common.exception.ApiAsserts;
import com.wyc.amor.model.entity.BmsDaily;
import com.wyc.amor.service.IBmsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日常记录
 *
 * @author Knox 2020/11/17
 */
@RestController
@RequestMapping("/daily")
@Api(tags = "BmsDailyController", description = "日常控制器")
public class BmsDailyController extends BaseController {

    @Resource
    private IBmsDailyService bmsDailyService;

    @GetMapping("/all")
    @ApiOperation(value = "日常列表")
    public ApiResult<List<BmsDaily>> list() {
        List<BmsDaily> list = bmsDailyService.list();
        return ApiResult.success(list);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加日常记录")
    public ApiResult<BmsDaily> save(@RequestBody BmsDaily daily) {
        if (ObjectUtils.isEmpty(daily)) {
            ApiAsserts.fail("参数不正确");
        }
        bmsDailyService.saveOrUpdate(daily);
        return ApiResult.success(daily);
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "查看日常")
    public ApiResult<BmsDaily> find(@PathVariable("id") Integer id) {
        BmsDaily byId = bmsDailyService.getById(id);
        if (ObjectUtils.isEmpty(byId)) {
            ApiAsserts.fail("数据不存在");
        }
        return ApiResult.success(byId);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除日常")
    public ApiResult<Object> delete(@PathVariable("id") Integer id) {
        BmsDaily byId = bmsDailyService.getById(id);
        if (ObjectUtils.isEmpty(byId)) {
            ApiAsserts.fail("来晚一步，数据已不存在");
        }
        bmsDailyService.removeById(id);
        return ApiResult.success("删除成功");
    }
}
