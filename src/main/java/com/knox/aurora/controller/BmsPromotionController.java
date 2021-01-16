package com.knox.aurora.controller;

import com.knox.aurora.common.api.ApiResult;
import com.knox.aurora.model.entity.BmsPromotion;
import com.knox.aurora.service.IBmsPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 广告推广控制器
 *
 * @author Knox 2020/11/7
 */
@RestController
@RequestMapping("/promotion")
@Api(tags = "BmsPromotionController", description = "广告推广控制器")
public class BmsPromotionController extends BaseController {

    @Resource
    private IBmsPromotionService bmsPromotionService;

    @GetMapping("/all")
    @ApiOperation(value = "获取推广集合")
    public ApiResult<List<BmsPromotion>> list() {
        List<BmsPromotion> list = bmsPromotionService.list();
        return ApiResult.success(list);
    }

}
