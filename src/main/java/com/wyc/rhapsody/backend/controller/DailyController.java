package com.wyc.rhapsody.backend.controller;

import com.wyc.rhapsody.backend.common.api.ApiResult;
import com.wyc.rhapsody.backend.model.entity.TbDaily;
import com.wyc.rhapsody.backend.service.DailyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日常记录
 *
 * @author Knox 2020/11/17
 */
@Api(tags = "日常控制器")
@RestController
@RequestMapping("/api/daily")
public class DailyController extends BaseController {

    @Autowired
    private DailyService dailyService;

    @GetMapping("/all")
    public ApiResult list() {
        List<TbDaily> list = dailyService.list();
        return ApiResult.success(list);
    }


    @PostMapping("/add")
    public ApiResult save(@RequestBody TbDaily daily) {
        Assert.notNull(daily, "参数不正确");
        dailyService.saveOrUpdate(daily);
        return ApiResult.success(daily);
    }


    @GetMapping("/find/{id}")
    public ApiResult find(@PathVariable("id") Integer id) {
        TbDaily byId = dailyService.getById(id);
        Assert.notNull(byId, "数据不存在");
        return ApiResult.success(byId);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult delete(@PathVariable("id") Integer id) {
        TbDaily byId = dailyService.getById(id);
        Assert.notNull(byId, "来晚一步，数据已不存在");
        dailyService.removeById(id);
        return ApiResult.success("删除成功");
    }
}
