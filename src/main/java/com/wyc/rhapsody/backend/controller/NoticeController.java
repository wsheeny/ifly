package com.wyc.rhapsody.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyc.rhapsody.backend.common.api.ApiResult;
import com.wyc.rhapsody.backend.model.entity.TbNotice;
import com.wyc.rhapsody.backend.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 公告
 *
 * @author Knox 2020/11/19
 */
@Api(tags = "全站公告处理器", value = "NoticeController")
@RestController
@RequestMapping("/api/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/show")
    @ApiOperation(value = "获取站点通告")
    public ApiResult<TbNotice> getNotice() {
        List<TbNotice> list = noticeService.list(new LambdaQueryWrapper<TbNotice>().eq(TbNotice::isShow, true));
        return ApiResult.success(list.get(list.size() - 1));
    }
}
