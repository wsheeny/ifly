package com.wyc.amor.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.amor.common.api.ApiResult;
import com.wyc.amor.model.vo.PostVO;
import com.wyc.amor.service.IBmsPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 全文检索
 *
 * @author Knox
 * @date 2020/12/5
 */
@RestController
@RequestMapping("/search")
@Api(tags = "BmsSearchController",description = "全文检索")
public class BmsSearchController extends BaseController {

    @Resource
    private IBmsPostService postService;

    @GetMapping
    @ApiOperation(value = "关键字检索")
    public ApiResult<Page<PostVO>> searchList(@RequestParam("keyword") String keyword,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        Page<PostVO> results = postService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return ApiResult.success(results);
    }

}
