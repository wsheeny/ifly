package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.BmsPromotion;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 广告
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface BmsPromotionMapper extends BaseMapper<BmsPromotion> {
}
