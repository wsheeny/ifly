package com.knox.aurora.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knox.aurora.mapper.BmsEventMapper;
import com.knox.aurora.model.entity.BmsActivity;
import com.knox.aurora.service.IBmsActivityService;
import org.springframework.stereotype.Service;

/**
 * 活动
 *
 * @author Knox 2020/11/21
 */
@Service
public class IBmsActivityServiceImpl extends ServiceImpl<BmsEventMapper, BmsActivity> implements IBmsActivityService {
}
