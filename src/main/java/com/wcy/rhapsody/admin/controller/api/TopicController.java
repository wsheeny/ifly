package com.wcy.rhapsody.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import com.wcy.rhapsody.admin.controller.BaseController;
import com.wcy.rhapsody.admin.core.R;
import com.wcy.rhapsody.admin.modules.dto.CreateTopicDTO;
import com.wcy.rhapsody.admin.modules.entity.Tag;
import com.wcy.rhapsody.admin.modules.entity.Topic;
import com.wcy.rhapsody.admin.modules.entity.TopicTag;
import com.wcy.rhapsody.admin.modules.entity.User;
import com.wcy.rhapsody.admin.modules.vo.CommentVO;
import com.wcy.rhapsody.admin.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 议题，话题控制器
 *
 * @author Yeeep 2020/11/7
 */
@Api(tags = "话题控制器")
@RestController
public class TopicController extends BaseController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicTagService topicTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;


    /**
     * 发布
     * <p>
     * 话题标题，内容，标签 ，分类
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "发布话题")
    @PostMapping("/api/topic/create")
    public R create(@RequestBody CreateTopicDTO dto) {
        User principal = getPrincipal();
        Assert.isTrue(principal.getActive(), "你的帐号还没有激活，请去个人设置页面激活帐号");
        Topic topic = topicService.create(dto, principal);
        return R.ok().data(topic);
    }


    /**
     * 浏览指定话题
     *
     * @param id 话题ID
     * @return
     */
    @ApiOperation(value = "获取指定话题,议题", notes = "输入话题ID获取")
    @ApiImplicitParam(paramType = "path", required = true, value = "话题ID", name = "id")
    @GetMapping("/api/topic/{id}")
    public R view(@PathVariable("id") String id, HttpServletRequest request) {
        Assert.hasText(id, "参数补全，请补全后再查");
        Map<String, Object> map = new HashMap<>(16);
        Topic topic = topicService.getById(id);
        // 查询话题详情
        Assert.notNull(topic, "当前话题不存在,或已被作者删除");
        User user = userService.getById(topic.getUserId());
        // 查询话题关联的标签
        QueryWrapper<TopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TopicTag::getTopicId, topic.getId());
        Set<String> set = new HashSet<>();
        for (TopicTag articleTag : topicTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<Tag> tags = tagService.listByIds(set);
        // TODO: 2020/10/29 根据访问IP过滤,话题浏览量+1

        topic.setView(topic.getView() + 1);
        topicService.updateById(topic);
        // emoji转码
        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        // 当前用户的其他主题
        List<Topic> userOtherArticles = topicService.selectAuthorOtherTopic(topic.getUserId(), topic.getId());

        // 评论
        List<CommentVO> commentsByTopicId = commentService.getCommentsByTopicId(id);

        map.put("topic", topic);
        map.put("tags", tags);
        map.put("otherTopics", userOtherArticles);
        map.put("user", user);
        map.put("comments", commentsByTopicId);
        return R.ok().data(map);
    }

    /**
     * 修改主题
     *
     * @param topic
     * @return
     */
    @PostMapping("/topic/update")
    public R update(@RequestBody Topic topic) {
        Assert.notNull(topic, "请检查参数是否正确");
        Topic byId = topicService.getById(topic.getId());
        Assert.notNull(byId, "来晚一步，主题已被删除");
        Assert.isTrue(byId.getUserId().equals(topic.getUserId()), "非本人无权修改");
        topic.setModifyTime(new Date());
        topicService.updateById(topic);
        return R.ok().data(topic);
    }


    /**
     * 获取用户发布的主题
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "获取用户发布的主题")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    @GetMapping("/topics/user/{id}")
    public R getTopicsByUserId(@PathVariable("id") String userId,
                               @ApiParam(name = "page", value = "页码，默认1") @RequestParam("page") Integer page,
                               @ApiParam(name = "size", value = "每页查询数量，默认10条一页") @RequestParam("size") Integer size) {

        // 用户发布的主题
        Page<Topic> topicPage = topicService.selectTopicsByUserId(userId, new Page<Topic>(page, size));
        return R.ok().data(topicPage);
    }

    /**
     * 获取随机推荐10篇
     *
     * @return
     */
    @ApiOperation(value = "获取随机10篇推荐话题")
    @GetMapping("/topics/recommend")
    public R getRecommend() {
        List<Topic> topics = topicService.getRecommend();
        return R.ok().data(topics);
    }

}
