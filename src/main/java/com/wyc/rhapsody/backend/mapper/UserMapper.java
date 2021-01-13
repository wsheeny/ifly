package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.rhapsody.backend.model.entity.ums.UmsUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<UmsUser> {

}
