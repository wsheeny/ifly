package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.BmsFriendLink;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 友链
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface BmsFriendLinkMapper extends BaseMapper<BmsFriendLink> {
}
