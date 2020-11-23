package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.entity.web.Tag;
import com.wcy.rhapsody.admin.modules.entity.web.Topic;
import com.wcy.rhapsody.admin.service.api.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 标签 控制器
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "标签控制器")
@RestController
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    /**
     * 获取标签列表
     *
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "获取标签列表", notes = "分页查询标签列表")
    @GetMapping("/tags")
    public R getTags(@ApiParam(name = "page", value = "页码，默认1", required = true) @RequestParam("page") Integer page,
                     @ApiParam(name = "size", value = "每页数据量，默认10", required = true) @RequestParam("size") Integer size) {
        Page<Tag> tagPage = tagService.page(new Page<>(page, size));
        return R.ok().data(tagPage);
    }


    /**
     * 获取关联话题
     *
     * @param tagName
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "获取标签关联文章", notes = "输入标签名称，获取关联话题")
    @ApiImplicitParam(name = "name", value = "标签名", required = true, paramType = "path")
    @GetMapping("/tag/{name}")
    public R getTopicsByTag(
            @PathVariable("name") String tagName,
            @ApiParam(name = "page", value = "页码,默认1", required = true) @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "size", value = "每页数据量。默认10", required = true) @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Map<String, Object> map = new HashMap<>(16);

        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getName, tagName);
        Tag one = tagService.getOne(wrapper);
        Assert.notNull(one, "话题不存在，或已被管理员删除");
        Page<Topic> topics = tagService.selectTopicsByTagId(new Page<>(page, size), one.getId());
        // 其他热门标签
        Page<Tag> hotTags = tagService.page(new Page<>(1, 10),
                new LambdaQueryWrapper<Tag>()
                        .notIn(Tag::getName, tagName)
                        .orderByDesc(Tag::getTopicCount));

        map.put("topics", topics);
        map.put("hotTags", hotTags);

        return R.ok().data(map);
    }
}
