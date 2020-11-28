package com.wcy.rhapsody.admin.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.api.DailyMapper;
import com.wcy.rhapsody.admin.model.entity.web.Daily;
import com.wcy.rhapsody.admin.service.api.DailyService;
import org.springframework.stereotype.Service;

/**
 * 日常实现类
 *
 * @author Yeeep 2020/11/17
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

}
