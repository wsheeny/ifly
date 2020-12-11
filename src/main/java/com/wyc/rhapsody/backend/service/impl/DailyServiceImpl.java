package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.rhapsody.backend.mapper.DailyMapper;
import com.wyc.rhapsody.backend.model.entity.TbDaily;
import com.wyc.rhapsody.backend.service.DailyService;
import org.springframework.stereotype.Service;

/**
 * 日常实现类
 *
 * @author Knox 2020/11/17
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, TbDaily> implements DailyService {

}
