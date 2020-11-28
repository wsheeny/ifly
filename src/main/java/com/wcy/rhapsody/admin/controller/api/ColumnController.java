package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.MyHttpCode;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.exception.MyException;
import com.wcy.rhapsody.admin.model.entity.web.Column;
import com.wcy.rhapsody.admin.model.entity.web.Topic;
import com.wcy.rhapsody.admin.model.vo.ColumnVO;
import com.wcy.rhapsody.admin.service.api.ColumnService;
import com.wcy.rhapsody.admin.service.api.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 用户话题专栏
 *
 * @author Yeeep
 * @date 2020/11/28
 */
@Api(tags = "专栏")
@RestController
@RequestMapping("/column")
public class ColumnController extends BaseController {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private TopicService topicService;

    /**
     * 专栏列表
     *
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "专栏列表", notes = "")
    @GetMapping("/all")
    public R list(/*@RequestBody Column column,*/
            @ApiParam(value = "page", name = "页码", required = true) @RequestParam("page") Integer page,
            @ApiParam(value = "size", name = "每页数据", required = true) @RequestParam("size") Integer size) {
        Page<ColumnVO> columnVO = columnService.getList(new Page<>(page, size));
        return R.ok().data(columnVO);
    }


    /**
     * 专栏下的话题
     *
     * @param title
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "专栏话题列表", notes = "")
    @ApiImplicitParam(value = "title", name = "专栏标题", paramType = "path", required = true)
    @GetMapping("/all/{title}")
    public R list(@PathVariable("title") String title,
                  @ApiParam(value = "page", name = "页码", required = true) @RequestParam("page") Integer page,
                  @ApiParam(value = "size", name = "每页数据", required = true) @RequestParam("size") Integer size) {
        Column one = columnService.getOne(new LambdaQueryWrapper<Column>().eq(Column::getTitle, title));
        if (StringUtils.isEmpty(one)) {
            throw new MyException().code(MyHttpCode.HTTP_NOT_FOUND).message("专栏不存在");
        }
        Page<Topic> topicPage = topicService.page(new Page<>(page, size), new LambdaQueryWrapper<Topic>().eq(Topic::getSectionId, one.getId()));
        return R.ok().data(topicPage);
    }
}
