package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.EventMapper;
import com.wcy.rhapsody.admin.model.entity.Event;
import com.wcy.rhapsody.admin.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * 活动
 *
 * @author Yeeep 2020/11/21
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<EventMapper, Event> implements ActivityService {
}
