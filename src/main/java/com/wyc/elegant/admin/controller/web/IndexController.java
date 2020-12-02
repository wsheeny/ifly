package com.wyc.elegant.admin.controller.web;

import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.controller.BaseController;
import com.wyc.elegant.admin.service.TopicService;
import com.wyc.elegant.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页路由
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "首页控制器")
@Controller
public class IndexController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    /**
     * 网站统计
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/size/count")
    @ApiOperation(value = "站点统计", notes = "统计")
    public R count() {
        Map<String, Object> map = new HashMap<>(16);

        /*会员数*/
        int count = userService.count();
        map.put("user", count);
        /*主题*/
        int count1 = topicService.count();
        map.put("topic", count1);

        return R.ok().data(map);
    }
}
