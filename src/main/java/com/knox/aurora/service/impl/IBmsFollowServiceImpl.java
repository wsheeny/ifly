package com.knox.aurora.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knox.aurora.mapper.BmsFollowMapper;
import com.knox.aurora.model.entity.BmsFollow;
import com.knox.aurora.service.IBmsFollowService;
import org.springframework.stereotype.Service;

/**
 * 关注实现类
 *
 * @author Knox 2020/11/20
 */
@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
