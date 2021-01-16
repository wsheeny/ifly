package com.knox.aurora.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ranking排行榜
 *
 * @author Knox 2020/11/17
 */
@RestController
@RequestMapping("/rank")
@Api(tags = "排行榜", description = "站内排行")
public class BmsRankingController extends BaseController {

}
