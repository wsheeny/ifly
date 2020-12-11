package com.wyc.rhapsody.backend.controller;

import com.wyc.rhapsody.backend.common.R;
import com.wyc.rhapsody.backend.model.entity.TbPromotion;
import com.wyc.rhapsody.backend.service.PromotionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 广告推广控制器
 *
 * @author Knox 2020/11/7
 */
@Api(tags = "广告推广控制器")
@RestController
@RequestMapping("/api/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    /**
     * 广告列表
     *
     * @return
     */
    @GetMapping("/all")
    public R list() {
        List<TbPromotion> list = promotionService.list();
        return R.ok().data(list);
    }

}
