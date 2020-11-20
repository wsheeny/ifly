package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.entity.Notice;
import com.wcy.rhapsody.admin.service.NoticeService;
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
@RequestMapping("/api/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/show")
    public R getNotice() {
        Notice one = noticeService.getOne(new LambdaQueryWrapper<Notice>().eq(Notice::isShow, true));
        return R.ok().data(one);
    }
}
