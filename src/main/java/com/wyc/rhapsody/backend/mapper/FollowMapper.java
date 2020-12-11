package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.rhapsody.backend.model.entity.TbFollow;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 关注Mapper
 *
 * @author Knox 2020/11/20
 */
@Mapper
@Repository
public interface FollowMapper extends BaseMapper<TbFollow> {
}
