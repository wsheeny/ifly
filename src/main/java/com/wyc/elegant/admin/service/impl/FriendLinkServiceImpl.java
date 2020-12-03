package com.wyc.elegant.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.mapper.FriendLinkMapper;
import com.wyc.elegant.admin.model.entity.TbFriendLink;
import com.wyc.elegant.admin.service.FriendLinkService;
import org.springframework.stereotype.Service;

/**
 * 友链 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, TbFriendLink> implements FriendLinkService {
}
