package com.wyc.buefy.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.buefy.web.model.entity.TbActivity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Knox 2020/11/21
 */
@Mapper
@Repository
public interface EventMapper extends BaseMapper<TbActivity> {
}
