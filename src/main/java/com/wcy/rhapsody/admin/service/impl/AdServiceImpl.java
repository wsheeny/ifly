package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.AdMapper;
import com.wcy.rhapsody.admin.modules.entity.Ad;
import com.wcy.rhapsody.admin.service.AdService;
import org.springframework.stereotype.Service;

/**
 * 广告 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

}
