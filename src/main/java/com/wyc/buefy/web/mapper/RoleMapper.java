package com.wyc.buefy.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.buefy.web.model.entity.TbRole;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<TbRole> {
}
