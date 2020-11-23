package com.wcy.rhapsody.admin.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.config.redis.RedisService;
import com.wcy.rhapsody.admin.mapper.api.TipMapper;
import com.wcy.rhapsody.admin.modules.entity.web.Tip;
import com.wcy.rhapsody.admin.service.api.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Tip实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class TipServiceImpl extends ServiceImpl<TipMapper, Tip> implements TipService {

    @Autowired
    private RedisService redisService;

    /**
     * 每日赠言，一天的时效
     *
     * @param type 类型
     * @return
     */
    @Override
    public Tip getRandomTip(Integer type) {
        Tip todayTip = (Tip) redisService.get("today_tip");
        if (StringUtils.isEmpty(todayTip)) {
            todayTip = this.baseMapper.getRandomTip(type);
            redisService.set("today_tip", todayTip, 24 * 60 * 60);
        }
        return todayTip;
    }
}
