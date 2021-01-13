package com.wyc.rhapsody.backend.controller;

import com.wyc.rhapsody.backend.common.api.ApiResult;
import com.wyc.rhapsody.backend.model.entity.TbFriendLink;
import com.wyc.rhapsody.backend.service.FriendLinkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ApiResult<List<TbFriendLink>> getLinks() {
        return ApiResult.success(friendLinkService.list());
    }
}
