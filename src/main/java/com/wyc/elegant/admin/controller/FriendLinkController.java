package com.wyc.elegant.admin.controller;

import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.service.FriendLinkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 友链
 *
 * @author Knox 2020/10/29
 */
@Api(tags = "友链控制器")
@RestController
@RequestMapping("/api/friend_link")
public class FriendLinkController extends BaseController {

    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 获取友链
     *
     * @return
     */
    @GetMapping("/all")
    public R getLinks() {
        return R.ok().data(friendLinkService.list());
    }
}
