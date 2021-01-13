package com.wyc.amor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.amor.common.api.ApiResult;
import com.wyc.amor.common.exception.ApiAsserts;
import com.wyc.amor.model.entity.BmsColumn;
import com.wyc.amor.model.vo.PostVO;
import com.wyc.amor.model.vo.BmsColumnVO;
import com.wyc.amor.service.IBmsColumnService;
import com.wyc.amor.service.IBmsPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户话题专栏
 *
 * @author Knox
 * @date 2020/11/28
 */
@RestController
@RequestMapping("/column")
@Api(tags = "BmsColumnController", description = "专栏")
public class BmsColumnController extends BaseController {

    @Resource
    private IBmsColumnService columnService;

    @Resource
    private IBmsPostService bmsPostService;

    @GetMapping("/all")
    @ApiOperation(value = "专栏列表")
    public ApiResult<Page<BmsColumnVO>> list(/*@RequestBody Column column,*/
            @ApiParam(value = "page", name = "页码", required = true) @RequestParam("page") Integer page,
            @ApiParam(value = "size", name = "每页数据", required = true) @RequestParam("size") Integer size) {
        Page<BmsColumnVO> columnVO = columnService.getList(new Page<>(page, size));
        return ApiResult.success(columnVO);
    }

    @GetMapping("/{title}/all")
    @ApiImplicitParam(value = "title", name = "专栏标题", paramType = "path", required = true)
    @ApiOperation(value = "专栏话题列表")
    public ApiResult<Page<PostVO>> list(@PathVariable("title") String title,
                                        @ApiParam(value = "page", name = "页码", required = true) @RequestParam("page") Integer page,
                                        @ApiParam(value = "size", name = "每页数据", required = true) @RequestParam("size") Integer size) {
        BmsColumn one = columnService.getOne(new LambdaQueryWrapper<BmsColumn>().eq(BmsColumn::getTitle, title));
        if (ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("专栏不存在");
        }
        Page<PostVO> topics = bmsPostService.selectByColumn(new Page<>(page, size), one);
        return ApiResult.success(topics);
    }
}
