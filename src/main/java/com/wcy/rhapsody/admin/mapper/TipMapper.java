package com.wcy.rhapsody.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wcy.rhapsody.admin.modules.entity.Tips;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

/**
 * Tip
 *
 * @author Yeeep 2020/11/7
 */
@Mapper
public interface TipMapper extends BaseMapper<Tips> {

    /**
     * 获取随机赠言
     *
     * @param type 类型
     * @return
     */
    @Select("select * from tips where type = #{type} order by rand() limit 1")
    Tips getRandomTip(@Param("type") Integer type);
}
