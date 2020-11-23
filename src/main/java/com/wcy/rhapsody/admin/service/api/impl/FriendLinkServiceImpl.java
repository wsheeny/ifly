package com.wcy.rhapsody.admin.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.api.FriendLinkMapper;
import com.wcy.rhapsody.admin.modules.entity.web.FriendLink;
import com.wcy.rhapsody.admin.service.api.FriendLinkService;
import org.springframework.stereotype.Service;

/**
 * 友链 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {
}
