package com.knox.aurora.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.knox.aurora.model.entity.BmsSystemConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Knox 2020/11/26
 */
@Mapper
@Repository
public interface BmsSystemConfigMapper extends BaseMapper<BmsSystemConfig> {

}
