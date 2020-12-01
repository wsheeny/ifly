package com.wcy.rhapsody.admin.controller.api;

import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.model.entity.Tip;
import com.wcy.rhapsody.admin.service.TipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Tips控制器
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "tips控制器")
@RestController
public class TipController extends BaseController {

    @Resource
    private TipService tipService;

    /**
     * 每日随机赠言，目前使用缓存
     *
     * @param type 类型
     * @return
     */
    @ApiOperation(value = "获取每日赠言", notes = "Type默认为1，一般情况使用默认值", httpMethod = "GET")
    @GetMapping("/today/tip")
    public R getRandomTip(@ApiParam("type类型，默认使用1") @RequestParam(value = "type", defaultValue = "1") Integer type) {
        Tip tip = tipService.getRandomTip(type);
        return R.ok().data(tip);
    }
}
