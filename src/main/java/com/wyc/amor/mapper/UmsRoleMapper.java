package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.UmsRole;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface UmsRoleMapper extends BaseMapper<UmsRole> {
}
