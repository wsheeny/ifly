package com.wyc.amor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.amor.mapper.BmsEventMapper;
import com.wyc.amor.model.entity.BmsActivity;
import com.wyc.amor.service.IBmsActivityService;
import org.springframework.stereotype.Service;

/**
 * 活动
 *
 * @author Knox 2020/11/21
 */
@Service
public class IBmsActivityServiceImpl extends ServiceImpl<BmsEventMapper, BmsActivity> implements IBmsActivityService {
}
