package com.knox.aurora.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.knox.aurora.model.entity.BmsBulletin;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 公告牌
 *
 * @author Knox 2020/11/19
 */
@Mapper
@Repository
public interface BmsBillboardMapper extends BaseMapper<BmsBulletin> {
}
