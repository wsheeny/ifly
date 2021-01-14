package com.wyc.amor.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import com.wyc.amor.common.api.ApiResult;
import com.wyc.amor.model.dto.CreateTopicDTO;
import com.wyc.amor.model.entity.BmsPost;
import com.wyc.amor.model.entity.UmsUser;
import com.wyc.amor.model.vo.PostVO;
import com.wyc.amor.service.IBmsPostService;
import com.wyc.amor.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 贴子处理器
 *
 * @author knox
 * @since 2021-01-13
 */
@RestController
@RequestMapping("/post")
@Api(tags = "PostController", description = "贴子处理器")
public class BmsPostController extends BaseController {

    @Resource
    private IBmsPostService iBmsPostService;

    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/list")
    @ApiOperation(value = "获取话题列表", notes = "分页查询，默认每页10条数据")
    public ApiResult<Page<PostVO>> list(
            @ApiParam("类别，如latest(最新)，hot(热门)，essence(加精)，top(置顶)， 默认查询latest")
            @RequestParam(value = "tab", defaultValue = "latest") String tab,
            @ApiParam("页码，默认值1") @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @ApiParam("每页展示数据量，默认10") @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iBmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "create", nickname = "发布推文")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResult<BmsPost> create(@RequestBody CreateTopicDTO dto, Principal principal) {
        UmsUser user = umsUserService.getUserByUsername(principal.getName());
        Assert.isTrue(user.getActive(), "你的帐号还没有激活，请去个人设置页面激活帐号");
        BmsPost topic = iBmsPostService.create(dto, user);
        return ApiResult.success(topic);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    public ApiResult<String> delete(@PathVariable("id") String id, Principal principal) {
        UmsUser umsUser = umsUserService.getUserByUsername(principal.getName());
        ;
        BmsPost byId = iBmsPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(umsUser.getId()), "你为什么可以删除别人的话题？？？");
        iBmsPostService.removeById(id);
        return ApiResult.success(null,"删除成功");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    @ApiOperation(value = "话题更新")
    public ApiResult<BmsPost> update(@Valid @RequestBody BmsPost post, Principal principal) {
        UmsUser umsUser = umsUserService.getUserByUsername(principal.getName());
        Assert.isTrue(umsUser.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        iBmsPostService.updateById(post);
        return ApiResult.success(post);
    }

    @GetMapping()
    @ApiOperation(value = "获取指定话题,议题", notes = "输入话题ID获取")
    public ApiResult<Map<String, Object>> view(@ApiParam(value = "id", name = "话题ID", required = true) @RequestParam("id") String id) {
        Map<String, Object> map = iBmsPostService.viewTopic(id);
        return ApiResult.success(map);
    }

    @ApiOperation(value = "获取详情页推荐")
    @GetMapping("/recommend")
    public ApiResult<List<BmsPost>> getRecommend(@RequestParam("topicId") String id) {
        List<BmsPost> topics = iBmsPostService.getRecommend(id);
        return ApiResult.success(topics);
    }

}
