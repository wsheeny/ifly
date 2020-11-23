package com.wcy.rhapsody.admin.controller.api;

import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.service.api.FriendLinkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 友链
 *
 * @author Yeeep 2020/10/29
 */
@Api(tags = "友链控制器")
@RestController
public class FriendLinkController extends BaseController {

    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 获取友链
     *
     * @return
     */
    @GetMapping("/friend_links")
    public R getLinks() {
        return R.ok().data(friendLinkService.list());
    }
}
