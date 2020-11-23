package com.wcy.rhapsody.admin.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.api.EventMapper;
import com.wcy.rhapsody.admin.modules.entity.web.Event;
import com.wcy.rhapsody.admin.service.api.EventService;
import org.springframework.stereotype.Service;

/**
 * 活动
 *
 * @author Yeeep 2020/11/21
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {
}
