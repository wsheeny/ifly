package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.BmsTag;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Tag
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface BmsTagMapper extends BaseMapper<BmsTag> {

}
