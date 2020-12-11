package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.rhapsody.backend.model.entity.TbActivity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Knox 2020/11/21
 */
@Mapper
@Repository
public interface EventMapper extends BaseMapper<TbActivity> {
}
