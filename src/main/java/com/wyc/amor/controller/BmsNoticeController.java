package com.wyc.amor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyc.amor.common.api.ApiResult;
import com.wyc.amor.model.entity.BmsNotice;
import com.wyc.amor.service.IBmsNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告
 *
 * @author Knox 2020/11/19
 */
@RestController
@RequestMapping("/notice")
@Api(value = "NoticeController", description = "全站公告处理器")
public class BmsNoticeController extends BaseController {

    @Resource
    private IBmsNoticeService bmsNoticeService;

    @GetMapping("/show")
    @ApiOperation(value = "获取站点通告")
    public ApiResult<BmsNotice> getNotice() {
        List<BmsNotice> list = bmsNoticeService.list(new LambdaQueryWrapper<BmsNotice>().eq(BmsNotice::isShow, true));
        return ApiResult.success(list.get(list.size() - 1));
    }
}
