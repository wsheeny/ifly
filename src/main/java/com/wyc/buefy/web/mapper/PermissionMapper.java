package com.wyc.buefy.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.buefy.web.model.entity.TbPermission;
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
public interface PermissionMapper extends BaseMapper<TbPermission> {

    /**
     * 获取用户权限
     *
     * @param userId 用户ID
     * @return 权限集合
     */
    List<TbPermission> selectList(@Param("userId") String userId);

}
