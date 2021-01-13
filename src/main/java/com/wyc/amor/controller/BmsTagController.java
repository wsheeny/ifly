package com.wyc.amor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.amor.common.api.ApiResult;
import com.wyc.amor.model.entity.BmsPost;
import com.wyc.amor.model.entity.BmsTag;
import com.wyc.amor.service.IBmsTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 标签 控制器
 *
 * @author Knox 2020/11/7
 */
@RestController
@RequestMapping("/tag")
@Api(tags = "BmsTagController", description = "标签处理器")
public class BmsTagController extends BaseController {

    @Resource
    private IBmsTagService bmsTagService;

    @GetMapping("/all")
    @ApiOperation(value = "标签列表")
    public ApiResult<Page<BmsTag>> list(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<BmsTag> page = bmsTagService.page(new Page<>(pageNum, pageSize), null);
        return ApiResult.success(page);
    }

    @GetMapping("/{name}")
    @ApiOperation(value = "获取标签关联文章", notes = "输入标签名称，获取关联话题")
    @ApiImplicitParam(name = "name", value = "标签名", required = true, paramType = "path")
    public ApiResult<Map<String, Object>> getTopicsByTag(
            @PathVariable("name") String tagName,
            @ApiParam(name = "page", value = "页码,默认1", required = true) @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "size", value = "每页数据量。默认10", required = true) @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Map<String, Object> map = new HashMap<>(16);

        LambdaQueryWrapper<BmsTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BmsTag::getName, tagName);
        BmsTag one = bmsTagService.getOne(wrapper);
        Assert.notNull(one, "话题不存在，或已被管理员删除");
        Page<BmsPost> topics = bmsTagService.selectTopicsByTagId(new Page<>(page, size), one.getId());
        // 其他热门标签
        Page<BmsTag> hotTags = bmsTagService.page(new Page<>(1, 10),
                new LambdaQueryWrapper<BmsTag>()
                        .notIn(BmsTag::getName, tagName)
                        .orderByDesc(BmsTag::getTopicCount));

        map.put("topics", topics);
        map.put("hotTags", hotTags);

        return ApiResult.success(map);
    }

}
