package com.knox.aurora.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.knox.aurora.model.entity.UmsUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface UmsUserMapper extends BaseMapper<UmsUser> {

}
