package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.TbDaily;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 日常
 *
 * @author Knox 2020/11/17
 */
@Mapper
@Repository
public interface BmsDailyMapper extends BaseMapper<TbDaily> {
}
