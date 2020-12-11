package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.rhapsody.backend.mapper.EventMapper;
import com.wyc.rhapsody.backend.model.entity.TbActivity;
import com.wyc.rhapsody.backend.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * 活动
 *
 * @author Knox 2020/11/21
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<EventMapper, TbActivity> implements ActivityService {
}
