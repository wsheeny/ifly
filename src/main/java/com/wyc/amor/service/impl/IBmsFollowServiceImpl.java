package com.wyc.amor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.amor.mapper.BmsFollowMapper;
import com.wyc.amor.model.entity.BmsFollow;
import com.wyc.amor.service.IBmsFollowService;
import org.springframework.stereotype.Service;

/**
 * 关注实现类
 *
 * @author Knox 2020/11/20
 */
@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
