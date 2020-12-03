package com.wyc.elegant.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.mapper.EventMapper;
import com.wyc.elegant.admin.model.entity.TbActivity;
import com.wyc.elegant.admin.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * 活动
 *
 * @author Knox 2020/11/21
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<EventMapper, TbActivity> implements ActivityService {
}
