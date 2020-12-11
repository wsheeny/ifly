package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.rhapsody.backend.model.entity.TbDaily;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 日常
 *
 * @author Knox 2020/11/17
 */
@Mapper
@Repository
public interface DailyMapper extends BaseMapper<TbDaily> {
}
