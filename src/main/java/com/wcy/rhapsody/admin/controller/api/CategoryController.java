package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.model.entity.web.Category;
import com.wcy.rhapsody.admin.model.vo.TopicVO;
import com.wcy.rhapsody.admin.service.api.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 分类
 *
 * @author Yeeep 2020/11/15
 */
@Api(tags = "分类控制器")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分类列表", notes = "分类列表")
    @PostMapping("/list")
    public R list(Category category,
                  @ApiParam(name = "page", value = "页码，默认1") @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                  @ApiParam(name = "size", value = "每页数量，默认10") @RequestParam(required = false, defaultValue = "10") int pageSize) {
        //条件构造器
        QueryWrapper<Category> queryWrapper = new QueryWrapper<Category>(category);
        //执行分页
        Page<Category> page = categoryService.page(new Page<>(pageNumber, pageSize), queryWrapper);
        //返回结果
        return R.ok().data(page);
    }

    /**
     * 查询指定类目的主题
     */
    @ApiOperation(value = "查询指定类目", notes = "分类列表")
    @GetMapping("/{name}")
    @ApiImplicitParam(name = "name", value = "分类名称", required = true, paramType = "path")
    public R listByCategory(@PathVariable("name") String categoryName,
                            @ApiParam(name = "page", value = "页码，默认1") @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                            @ApiParam(name = "size", value = "每页数量，默认10") @RequestParam(required = false, defaultValue = "10") int pageSize) {
        Category category = categoryService.getOne(new LambdaQueryWrapper<Category>().eq(Category::getName, categoryName));
        Assert.notNull(category, "当前分类不存在");

        // 当前类目下的主题
        IPage<TopicVO> page = categoryService.listByCategoryName(category, new Page<TopicVO>(pageNumber, pageSize));
        //返回结果
        return R.ok().data(page);
    }
}
