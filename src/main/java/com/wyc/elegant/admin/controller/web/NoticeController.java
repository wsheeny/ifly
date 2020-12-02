package com.wyc.elegant.admin.controller.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.controller.BaseController;
import com.wyc.elegant.admin.model.entity.Notice;
import com.wyc.elegant.admin.service.NoticeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公告
 *
 * @author Yeeep 2020/11/19
 */
@Api(tags = "全站公告处理器")
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/show")
    public R getNotice() {
        Notice one = noticeService.getOne(new LambdaQueryWrapper<Notice>().eq(Notice::isShow, true));
        return R.ok().data(one);
    }
}
