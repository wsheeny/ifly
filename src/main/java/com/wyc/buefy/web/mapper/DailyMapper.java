package com.wyc.buefy.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.buefy.web.model.entity.TbDaily;
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
