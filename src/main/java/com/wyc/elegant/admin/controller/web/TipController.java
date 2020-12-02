package com.wyc.elegant.admin.controller.web;

import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.controller.BaseController;
import com.wyc.elegant.admin.model.entity.Tip;
import com.wyc.elegant.admin.service.TipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Tips控制器
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "tips控制器")
@RestController
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
    @GetMapping("/today/tip")
    public R getRandomTip(@ApiParam("type类型，默认使用1") @RequestParam(value = "type", defaultValue = "1") Integer type) {
        Tip tip = tipService.getRandomTip(type);
        return R.ok().data(tip);
    }
}
