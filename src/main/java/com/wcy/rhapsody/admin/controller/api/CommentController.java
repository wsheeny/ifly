package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.dto.CommentDTO;
import com.wcy.rhapsody.admin.modules.entity.Comment;
import com.wcy.rhapsody.admin.modules.entity.User;
import com.wcy.rhapsody.admin.modules.vo.CommentVO;
import com.wcy.rhapsody.admin.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论 控制器
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "评论控制器")
@RestController
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;


    /**
     * 发表评论
     *
     * @param dto
     * @return
     */
    @PostMapping("/api/comment")
    public R create(@RequestBody CommentDTO dto) {
        Assert.notNull(dto, "参数不正确");
        // 登录者
        User user = getPrincipal();
        Assert.isTrue(user.getActive(), "你的帐号还没有激活，请去个人设置页面激活帐号");

        Comment comment = commentService.insert(dto, user);
        // 过滤评论内容
        comment.setContent(comment.getContent());
        return R.ok().data(comment);
    }

    /**
     * 获取主题评论
     *
     * @param topicId
     * @return
     */
    @ApiOperation(value = "获取主题的评论", notes = "根据主题ID获取")
    @GetMapping("/api/comments")
    public R getCommentsByTopicId(
            @ApiParam(name = "topicId", value = "主题ID", required = true) @RequestParam("topicId") String topicId) {
        Assert.hasText(topicId, "参数不能为空");
        List<CommentVO> vos = commentService.getCommentsByTopicId(topicId);
        return R.ok().data(vos);
    }


    /**
     * 根据用户ID查询评论
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "根据用户ID获取用户发表的评论")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    @GetMapping("/comments/user/{id}")
    public R getCommentsByUserId(@PathVariable("id") String userId,
                                 @ApiParam(name = "page", value = "页码，默认1") @RequestParam("page") Integer page,
                                 @ApiParam(name = "size", value = "每页数据量，默认10") @RequestParam("size") Integer size) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getUserId, userId);
        Page<Comment> commentPage = commentService.page(new Page<>(page, size), wrapper);
        return R.ok().data(commentPage);
    }
}
