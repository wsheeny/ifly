package com.knox.aurora.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.knox.aurora.model.entity.UmsRolePermission;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色权限
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface UmsRolePermissionMapper extends BaseMapper<UmsRolePermission> {
}
