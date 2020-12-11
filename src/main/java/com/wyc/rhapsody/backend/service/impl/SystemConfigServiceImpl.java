package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.rhapsody.backend.mapper.SystemConfigMapper;
import com.wyc.rhapsody.backend.model.entity.TbSystemConfig;
import com.wyc.rhapsody.backend.service.SystemConfigService;
import org.springframework.stereotype.Service;

/**
 * @author Knox 2020/11/26
 */
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, TbSystemConfig> implements SystemConfigService {

}
