package com.wcy.rhapsody.admin.controller.api;

import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.entity.web.Daily;
import com.wcy.rhapsody.admin.service.api.DailyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日常记录
 *
 * @author Yeeep 2020/11/17
 */
@Api(tags = "日常控制器")
@RestController
@RequestMapping("/daily")
public class DailyController extends BaseController {
    @Autowired
    private DailyService dailyService;

    @GetMapping("/all")
    public R list() {
        List<Daily> list = dailyService.list();
        return R.ok().data(list);
    }


    @PostMapping("/add")
    public R save(@RequestBody Daily daily) {
        Assert.notNull(daily, "参数不正确");
        dailyService.saveOrUpdate(daily);
        return R.ok();
    }


    @GetMapping("/find/{id}")
    public R find(@PathVariable("id") Integer id) {
        Daily byId = dailyService.getById(id);
        Assert.notNull(byId, "数据不存在");
        return R.ok().data(byId);
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        Daily byId = dailyService.getById(id);
        Assert.notNull(byId, "来晚一步，数据已不存在");
        dailyService.removeById(id);
        return R.ok();
    }
}
