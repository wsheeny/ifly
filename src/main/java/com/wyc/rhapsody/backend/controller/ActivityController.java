package com.wyc.rhapsody.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.rhapsody.backend.common.R;
import com.wyc.rhapsody.backend.model.entity.TbActivity;
import com.wyc.rhapsody.backend.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动
 *
 * @author Knox 2020/11/21
 */
@RestController
@RequestMapping("/api/activity")
@Api(tags = "活动处理器")
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService eventService;

    /**
     * 获取活动列表
     *
     * @param event
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/all")
    @ApiOperation(value = "获取活动列表", notes = "可分页，根据参数查询")
    public R list(TbActivity event,
                  @ApiParam(name = "page", value = "页码，默认1")
                  @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                  @ApiParam(name = "size", value = "每页查询数量，默认10")
                  @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        Page<TbActivity> page1 = eventService.page(
                new Page<>(page, size),
                new LambdaQueryWrapper<>(event).orderByDesc(TbActivity::getStatus)
                        .orderByAsc(TbActivity::getTime));
        return R.ok().data(page1);
    }

}
