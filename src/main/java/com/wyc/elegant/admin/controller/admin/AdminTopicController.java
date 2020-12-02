package com.wyc.elegant.admin.controller.admin;

import com.wyc.elegant.admin.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台系统-话题处理器
 *
 * @author Yeeep 2020/11/23
 */
@Api(tags = "后台系统-话题处理器")
@RestController
@RequestMapping("/admin/topic")
public class AdminTopicController extends BaseController {
}
