package com.wcy.rhapsody.admin.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.SystemConfigMapper;
import com.wcy.rhapsody.admin.model.entity.web.TbSystemConfig;
import org.springframework.stereotype.Service;

/**
 * @author Yeeep 2020/11/26
 */
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, TbSystemConfig> implements SystemConfigService {

}
