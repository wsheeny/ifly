package com.wcy.rhapsody.admin.controller.api;

import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.service.api.TopicService;
import com.wcy.rhapsody.admin.service.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页路由
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "首页控制器")
@RestController
public class IndexController extends BaseController {
    @Resource
    private UserService userService;

    @Resource
    private TopicService topicService;

    /**
     * 网站统计
     *
     * @return
     */
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
