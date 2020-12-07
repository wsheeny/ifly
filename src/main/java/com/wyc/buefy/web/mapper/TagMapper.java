package com.wyc.buefy.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.buefy.web.model.entity.TbTag;
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
