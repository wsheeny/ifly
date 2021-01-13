package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.ums.UmsPermission;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {

    /**
     * 获取用户权限
     *
     * @param userId 用户ID
     * @return 权限集合
     */
    List<UmsPermission> selectList(@Param("userId") String userId);

}
