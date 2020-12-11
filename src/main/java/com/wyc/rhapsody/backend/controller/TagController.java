package com.wyc.rhapsody.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.rhapsody.backend.common.R;
import com.wyc.rhapsody.backend.model.entity.TbTag;
import com.wyc.rhapsody.backend.model.entity.TbTopic;
import com.wyc.rhapsody.backend.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 标签 控制器
 *
 * @author Knox 2020/11/7
 */
@Api(tags = "标签控制器")
@RestController
@RequestMapping("/api/tag")
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    /**
     * 获取关联话题
     */
    @ApiOperation(value = "获取标签关联文章", notes = "输入标签名称，获取关联话题")
    @ApiImplicitParam(name = "name", value = "标签名", required = true, paramType = "path")
    @GetMapping("/{name}")
    public R getTopicsByTag(
            @PathVariable("name") String tagName,
            @ApiParam(name = "page", value = "页码,默认1", required = true) @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "size", value = "每页数据量。默认10", required = true) @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Map<String, Object> map = new HashMap<>(16);

        LambdaQueryWrapper<TbTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TbTag::getName, tagName);
        TbTag one = tagService.getOne(wrapper);
        Assert.notNull(one, "话题不存在，或已被管理员删除");
        Page<TbTopic> topics = tagService.selectTopicsByTagId(new Page<>(page, size), one.getId());
        // 其他热门标签
        Page<TbTag> hotTags = tagService.page(new Page<>(1, 10),
                new LambdaQueryWrapper<TbTag>()
                        .notIn(TbTag::getName, tagName)
                        .orderByDesc(TbTag::getTopicCount));

        map.put("topics", topics);
        map.put("hotTags", hotTags);

        return R.ok().data(map);
    }

    /**
     * 结合
     *
     * @return
     */
    @GetMapping("/all")
    public R list(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return R.ok();
    }
}
