package com.wyc.elegant.admin.controller.web;

import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ranking排行榜
 *
 * @author Yeeep 2020/11/17
 */
@Api(tags = "排行榜")
@RestController
@RequestMapping("/ranking")
public class RankingController extends BaseController {


    public R list() {


        return R.ok();
    }
}
