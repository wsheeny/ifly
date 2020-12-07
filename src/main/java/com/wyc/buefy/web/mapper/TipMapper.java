package com.wyc.buefy.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.buefy.web.model.entity.TbTip;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Tip
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface TipMapper extends BaseMapper<TbTip> {

    /**
     * 获取随机赠言
     *
     * @param type 类型
     * @return
     */
    TbTip getRandomTip(@Param("type") Integer type);
}
