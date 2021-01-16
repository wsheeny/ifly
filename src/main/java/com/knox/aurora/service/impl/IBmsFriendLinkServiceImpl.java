package com.knox.aurora.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knox.aurora.mapper.BmsFriendLinkMapper;
import com.knox.aurora.model.entity.BmsFriendLink;
import com.knox.aurora.service.IBmsFriendLinkService;
import org.springframework.stereotype.Service;

/**
 * 友链 实现类
 *
 * @author Knox 2020/11/7
 */
@Service
public class IBmsFriendLinkServiceImpl extends ServiceImpl<BmsFriendLinkMapper, BmsFriendLink> implements IBmsFriendLinkService {
}
