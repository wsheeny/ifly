package com.wyc.rhapsody.backend.controller;

import com.wyc.rhapsody.backend.common.R;
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
    public R list() {
        List<TbDaily> list = dailyService.list();
        return R.ok().data(list);
    }


    @PostMapping("/add")
    public R save(@RequestBody TbDaily daily) {
        Assert.notNull(daily, "参数不正确");
        dailyService.saveOrUpdate(daily);
        return R.ok();
    }


    @GetMapping("/find/{id}")
    public R find(@PathVariable("id") Integer id) {
        TbDaily byId = dailyService.getById(id);
        Assert.notNull(byId, "数据不存在");
        return R.ok().data(byId);
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        TbDaily byId = dailyService.getById(id);
        Assert.notNull(byId, "来晚一步，数据已不存在");
        dailyService.removeById(id);
        return R.ok();
    }
}
