package com.wcy.rhapsody.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wcy.rhapsody.admin.model.entity.Permission;
import org.mapstruct.Mapper;

/**
 * 权限
 *
 * @author Yeeep 2020/11/7
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
