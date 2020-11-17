package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.CommentMapper;
import com.wcy.rhapsody.admin.modules.dto.CommentDTO;
import com.wcy.rhapsody.admin.modules.entity.Comment;
import com.wcy.rhapsody.admin.modules.entity.Topic;
import com.wcy.rhapsody.admin.modules.entity.User;
import com.wcy.rhapsody.admin.modules.vo.CommentVO;
import com.wcy.rhapsody.admin.service.CommentService;
import com.wcy.rhapsody.admin.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * 评论 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Autowired
    private TopicService topicService;

    @Override
    public Comment insert(CommentDTO dto, User user) {
        // 评论内容
        String content = dto.getContent();
        Assert.hasText(content, "请输入评论内容");

        String topicId = dto.getTopicId();
        Assert.hasText(topicId, "话题ID呢？");

        // 被回复评论ID
        String commentId = dto.getCommentId();

        Topic topic = topicService.getById(topicId);
        Assert.notNull(topic, "你晚了一步，话题可能已经被删除了");

        // 组装comment对象
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setContent(content);
        comment.setCreateTime(new Date());
        comment.setTopicId(topic.getId());
        comment.setUserId(user.getId());
        // 评论
        this.baseMapper.insert(comment);

        // 话题的评论数+1
        topic.setComments(topic.getComments() + 1);
        topicService.update(topic, null);

        // TODO: 2020/11/13 给作者发信息

        return comment;
    }


    @Override
    public List<CommentVO> getCommentsByTopicId(String topicId) {
        return this.baseMapper.selectCommentsByTopicId(topicId);
    }
}
