package com.wyc.amor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.amor.mapper.BmsFriendLinkMapper;
import com.wyc.amor.model.entity.TbFriendLink;
import com.wyc.amor.service.IBmsFriendLinkService;
import org.springframework.stereotype.Service;

/**
 * 友链 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class IBmsFriendLinkServiceImpl extends ServiceImpl<BmsFriendLinkMapper, TbFriendLink> implements IBmsFriendLinkService {
}
