package com.wcy.rhapsody.admin.mapper.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wcy.rhapsody.admin.modules.entity.web.Comment;
import com.wcy.rhapsody.admin.modules.vo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 评论Mapper
 *
 * @author Yeeep 2020/11/7
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {


    /**
     * 主题评论列表
     *
     * @param topicId
     * @return
     */
    List<CommentVO> selectCommentsByTopicId(@Param("topicId") String topicId);

}
