package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.BmsSystemConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Knox 2020/11/26
 */
@Mapper
@Repository
public interface BmsSystemConfigMapper extends BaseMapper<BmsSystemConfig> {

}
