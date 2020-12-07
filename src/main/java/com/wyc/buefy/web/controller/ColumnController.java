package com.wyc.buefy.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.buefy.web.common.MyHttpCode;
import com.wyc.buefy.web.common.R;
import com.wyc.buefy.web.exception.MyException;
import com.wyc.buefy.web.model.entity.TbColumn;
import com.wyc.buefy.web.model.vo.TbColumnVO;
import com.wyc.buefy.web.model.vo.TopicVO;
import com.wyc.buefy.web.service.ColumnService;
import com.wyc.buefy.web.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 用户话题专栏
 *
 * @author Knox
 * @date 2020/11/28
 */
@Api(tags = "专栏")
@RestController
@RequestMapping("/api/column")
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
        Page<TbColumnVO> columnVO = columnService.getList(new Page<>(page, size));
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
    @GetMapping("/{title}/all")
    public R list(@PathVariable("title") String title,
                  @ApiParam(value = "page", name = "页码", required = true) @RequestParam("page") Integer page,
                  @ApiParam(value = "size", name = "每页数据", required = true) @RequestParam("size") Integer size) {
        TbColumn one = columnService.getOne(new LambdaQueryWrapper<TbColumn>().eq(TbColumn::getTitle, title));
        if (ObjectUtils.isEmpty(one)) {
            throw new MyException().code(MyHttpCode.HTTP_NOT_FOUND).message("专栏不存在");
        }
        Page<TopicVO> topics = topicService.selectByColumn(new Page<>(page, size), one);
        return R.ok().data(topics);
    }
}
