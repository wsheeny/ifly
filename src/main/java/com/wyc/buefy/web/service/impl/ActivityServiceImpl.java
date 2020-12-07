package com.wyc.buefy.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.buefy.web.mapper.EventMapper;
import com.wyc.buefy.web.model.entity.TbActivity;
import com.wyc.buefy.web.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * 活动
 *
 * @author Knox 2020/11/21
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<EventMapper, TbActivity> implements ActivityService {
}
