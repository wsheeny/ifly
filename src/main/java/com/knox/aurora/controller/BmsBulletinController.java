package com.knox.aurora.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.knox.aurora.common.api.ApiResult;
import com.knox.aurora.model.entity.BmsBulletin;
import com.knox.aurora.service.IBmsBulletinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 站内公告 <Bulletin>
 *
 * @author Knox 2020/11/19
 */
@RestController
@RequestMapping("/bulletin")
@Api(tags = "BmsBulletinController", description = "全站公告处理器")
public class BmsBulletinController extends BaseController {

    @Resource
    private IBmsBulletinService bulletinService;

    @GetMapping("/show")
    @ApiOperation(value = "获取最新站内公告")
    public ApiResult<BmsBulletin> getNotice() {
        List<BmsBulletin> list = bulletinService.list(new LambdaQueryWrapper<BmsBulletin>().eq(BmsBulletin::isShow, true));
        return ApiResult.success(list.get(list.size() - 1));
    }
}
