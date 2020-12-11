package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.rhapsody.backend.model.entity.TbPromotion;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 广告
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface PromotionMapper extends BaseMapper<TbPromotion> {
}
