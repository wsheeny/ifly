package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.model.entity.web.Event;
import com.wcy.rhapsody.admin.service.api.EventService;
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
 * @author Yeeep 2020/11/21
 */
@RestController
@RequestMapping("/event")
@Api(tags = "活动处理器")
public class EventController extends BaseController {

    @Resource
    private EventService eventService;

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
    public R list(Event event,
                  @ApiParam(name = "page", value = "页码，默认1")
                  @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                  @ApiParam(name = "size", value = "每页查询数量，默认10")
                  @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        Page<Event> page1 = eventService.page(
                new Page<>(page, size),
                new LambdaQueryWrapper<>(event).orderByDesc(Event::getStatus)
                        .orderByAsc(Event::getTime));
        return R.ok().data(page1);
    }

}
