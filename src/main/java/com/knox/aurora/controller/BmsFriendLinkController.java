package com.knox.aurora.controller;

import com.knox.aurora.common.api.ApiResult;
import com.knox.aurora.model.entity.BmsFriendLink;
import com.knox.aurora.service.IBmsFriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友链
 *
 * @author Knox 2020/10/29
 */
@RestController
@RequestMapping("/friend/link")
@Api(tags = "BmsFriendLinkController", description = "友链控制器")
public class BmsFriendLinkController extends BaseController {

    @Resource
    private IBmsFriendLinkService bmsFriendLinkService;

    @GetMapping("/all")
    @ApiOperation(value = "获取友链集合")
    public ApiResult<List<BmsFriendLink>> getLinks() {
        return ApiResult.success(bmsFriendLinkService.list());
    }
}
