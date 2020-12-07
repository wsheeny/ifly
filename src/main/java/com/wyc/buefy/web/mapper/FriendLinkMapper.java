package com.wyc.buefy.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.buefy.web.model.entity.TbFriendLink;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 友链
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface FriendLinkMapper extends BaseMapper<TbFriendLink> {
}
