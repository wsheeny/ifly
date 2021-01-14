package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.BmsJourney;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 旅行
 *
 * @author Knox 2020/11/17
 */
@Mapper
@Repository
public interface BmsJourneyMapper extends BaseMapper<BmsJourney> {
}
