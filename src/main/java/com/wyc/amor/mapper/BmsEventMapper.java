package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.TbActivity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Knox 2020/11/21
 */
@Mapper
@Repository
public interface BmsEventMapper extends BaseMapper<TbActivity> {
}
