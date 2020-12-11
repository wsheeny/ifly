package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.rhapsody.backend.model.entity.TbSystemConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Knox 2020/11/26
 */
@Mapper
@Repository
public interface SystemConfigMapper extends BaseMapper<TbSystemConfig> {

}
