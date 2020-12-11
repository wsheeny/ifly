package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.rhapsody.backend.config.redis.RedisService;
import com.wyc.rhapsody.backend.mapper.TipMapper;
import com.wyc.rhapsody.backend.model.entity.TbTip;
import com.wyc.rhapsody.backend.service.TipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Tip实现类
 *
 * @author Knox 2020/11/7
 */
@Slf4j
@Service
public class TipServiceImpl extends ServiceImpl<TipMapper, TbTip> implements TipService {

    @Autowired
    private RedisService redisService;

    /**
     * 每日赠言，一天的时效
     *
     * @param type 类型
     * @return
     */
    @Override
    public TbTip getRandomTip(Integer type) {
        TbTip todayTip = null;
        try {
            todayTip = (TbTip) redisService.get("today_tip");
            if (ObjectUtils.isEmpty(todayTip)) {
                todayTip = this.baseMapper.getRandomTip(type);
                redisService.set("today_tip", todayTip, 24 * 60 * 60);
            }
        } catch (Exception e) {
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
