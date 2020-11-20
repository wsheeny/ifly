package com.wcy.rhapsody.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wcy.rhapsody.admin.modules.entity.User;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import sun.plugin.util.UserProfile;

/**
 * 用户
 *
 * @author Yeeep 2020/11/7
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
