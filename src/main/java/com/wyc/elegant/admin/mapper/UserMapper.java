package com.wyc.elegant.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.elegant.admin.model.entity.TbUser;
import org.mapstruct.Mapper;

/**
 * 用户
 *
 * @author Knox 2020/11/7
 */
@Mapper
public interface UserMapper extends BaseMapper<TbUser> {

}
