package com.wyc.elegant.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.elegant.admin.model.entity.TbPermission;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 权限
 *
 * @author Knox 2020/11/7
 */
@Mapper
public interface PermissionMapper extends BaseMapper<TbPermission> {

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    List<TbPermission> selectList(@Param("userId") String userId);

}
