package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.model.entity.web.Notice;
import com.wcy.rhapsody.admin.service.api.NoticeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公告
 *
 * @author Yeeep 2020/11/19
 */
@Api(tags = "全站公告处理器")
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @Resource
    private NoticeService noticeService;

    @GetMapping("/show")
    public R getNotice() {
        Notice one = noticeService.getOne(new LambdaQueryWrapper<Notice>().eq(Notice::isShow, true));
        return R.ok().data(one);
    }
}
