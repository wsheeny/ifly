package com.knox.aurora.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.knox.aurora.model.entity.BmsTip;
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
public interface BmsTipMapper extends BaseMapper<BmsTip> {

    /**
     * 获取随机赠言
     *
     * @param type 类型
     * @return
     */
    BmsTip getRandomTip(@Param("type") Integer type);
}
