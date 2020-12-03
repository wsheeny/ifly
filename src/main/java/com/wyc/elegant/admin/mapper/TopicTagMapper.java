package com.wyc.elegant.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.elegant.admin.model.entity.TbTopicTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Topic
 *
 * @author Knox 2020/11/7
 */
@Service
public interface TopicTagMapper extends BaseMapper<TbTopicTag> {

    /**
     * 根据标签获取话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> getTopicIdsByTagId(@Param("id") String id);
}
