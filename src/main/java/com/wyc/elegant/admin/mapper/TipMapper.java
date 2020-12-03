package com.wyc.elegant.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.elegant.admin.model.entity.TbTip;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * Tip
 *
 * @author Knox 2020/11/7
 */
@Mapper
public interface TipMapper extends BaseMapper<TbTip> {

    /**
     * 获取随机赠言
     *
     * @param type 类型
     * @return
     */
    TbTip getRandomTip(@Param("type") Integer type);
}
