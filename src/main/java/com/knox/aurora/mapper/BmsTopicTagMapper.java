package com.knox.aurora.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.knox.aurora.model.entity.BmsTopicTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Topic
 *
 * @author Knox 2020/11/7
 */
@Service
@Repository
public interface BmsTopicTagMapper extends BaseMapper<BmsTopicTag> {

    /**
     * 根据标签获取话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> getTopicIdsByTagId(@Param("id") String id);
}
