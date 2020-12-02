package com.wyc.elegant.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.elegant.admin.model.entity.Tip;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

/**
 * Tip
 *
 * @author Yeeep 2020/11/7
 */
@Mapper
public interface TipMapper extends BaseMapper<Tip> {

    /**
     * 获取随机赠言
     *
     * @param type 类型
     * @return
     */
    @Select("select * from tip where type = #{type} order by rand() limit 1")
    Tip getRandomTip(@Param("type") Integer type);
}
