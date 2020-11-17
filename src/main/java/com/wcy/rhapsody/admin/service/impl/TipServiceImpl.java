package com.wcy.rhapsody.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.config.redis.RedisService;
import com.wcy.rhapsody.admin.mapper.TipMapper;
import com.wcy.rhapsody.admin.modules.entity.Tips;
import com.wcy.rhapsody.admin.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Tip实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
public class TipServiceImpl extends ServiceImpl<TipMapper, Tips> implements TipService {

    @Autowired
    private RedisService redisService;

    /**
     * 每日赠言，一天的时效
     *
     * @param type 类型
     * @return
     */
    @Override
    public Tips getRandomTip(Integer type) {
        Tips todayTip = (Tips) redisService.get("today_tip");
        if (StringUtils.isEmpty(todayTip)) {
            todayTip = this.baseMapper.getRandomTip(type);
            redisService.set("today_tip", todayTip, 24 * 60 * 60);
        }
        return todayTip;
    }
}
