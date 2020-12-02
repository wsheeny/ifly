package com.wyc.elegant.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.mapper.AdMapper;
import com.wyc.elegant.admin.model.entity.Ad;
import com.wyc.elegant.admin.service.AdService;
import org.springframework.stereotype.Service;

/**
 * 广告 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

}
