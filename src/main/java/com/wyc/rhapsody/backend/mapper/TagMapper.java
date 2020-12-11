package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.rhapsody.backend.model.entity.TbTag;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Tag
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface TagMapper extends BaseMapper<TbTag> {

}
