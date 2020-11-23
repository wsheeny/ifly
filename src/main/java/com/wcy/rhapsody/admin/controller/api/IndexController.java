package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.vo.TopicVO;
import com.wcy.rhapsody.admin.service.api.CommentService;
import com.wcy.rhapsody.admin.service.api.TopicService;
import com.wcy.rhapsody.admin.service.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页路由
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "首页控制器")
@RestController
@RequestMapping("/api")
public class IndexController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private CommentService commentService;

    /**
     * 首页获取话题列表
     *
     * @return {@link R}
     */
    @ApiOperation(value = "获取话题列表", notes = "分页查询，默认每页10条数据")
    @GetMapping("/topics")
    public R getTopicList(
            @ApiParam("页码，默认值1") @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @ApiParam("每页展示数据量，默认10") @RequestParam(value = "size", defaultValue = "10") Integer size,
            @ApiParam("类别，如latest(最新)，hot(热门)，essence(加精)，top(置顶)， 默认查询latest")
            @RequestParam(value = "tab", defaultValue = "latest") String tab) {
        Page<TopicVO> voPage = topicService.getTopicListAndPage(new Page<>(pageNo, size), tab);
        return R.ok().data(voPage).message("首页话题列表接口调用成功");
    }

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
        /*回帖数*/
        int count2 = commentService.count();
        map.put("comment", count2);
        return R.ok().data(map);
    }
}
