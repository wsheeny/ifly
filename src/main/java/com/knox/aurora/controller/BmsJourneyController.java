package com.knox.aurora.controller;

import com.knox.aurora.common.api.ApiResult;
import com.knox.aurora.common.exception.ApiAsserts;
import com.knox.aurora.model.entity.BmsJourney;
import com.knox.aurora.service.IBmsJourneyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 旅行
 *
 * @author Knox 2020/11/17
 */
@RestController
@RequestMapping("/journey")
@Api(tags = "BmsDailyController", description = "旅行处理器")
public class BmsJourneyController extends BaseController {

    @Resource
    private IBmsJourneyService bmsDailyService;

    @GetMapping("/all")
    @ApiOperation(value = "日常列表")
    public ApiResult<List<BmsJourney>> list() {
        List<BmsJourney> list = bmsDailyService.list();
        return ApiResult.success(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查看日常")
    public ApiResult<BmsJourney> find(@PathVariable("id") Integer id) {
        BmsJourney byId = bmsDailyService.getById(id);
        if (ObjectUtils.isEmpty(byId)) {
            ApiAsserts.fail("数据不存在");
        }
        return ApiResult.success(byId);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加日常记录")
    public ApiResult<BmsJourney> save(@RequestBody BmsJourney daily) {
        if (ObjectUtils.isEmpty(daily)) {
            ApiAsserts.fail("参数不正确");
        }
        bmsDailyService.saveOrUpdate(daily);
        return ApiResult.success(daily);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除日常")
    public ApiResult<Object> delete(@PathVariable("id") Integer id) {
        BmsJourney byId = bmsDailyService.getById(id);
        if (ObjectUtils.isEmpty(byId)) {
            ApiAsserts.fail("来晚一步，数据已不存在");
        }
        bmsDailyService.removeById(id);
        return ApiResult.success(null, "删除成功");
    }
}
