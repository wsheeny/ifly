package com.knox.aurora.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knox.aurora.config.redis.RedisService;
import com.knox.aurora.mapper.BmsTipMapper;
import com.knox.aurora.model.entity.BmsTip;
import com.knox.aurora.service.IBmsTipService;
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
public class IBmsTipServiceImpl extends ServiceImpl<BmsTipMapper, BmsTip> implements IBmsTipService {

    @Autowired
    private RedisService redisService;

    /**
     * 每日赠言，一天的时效
     *
     * @param type 类型
     * @return
     */
    @Override
    public BmsTip getRandomTip(Integer type) {
        BmsTip todayTip = null;
        try {
            todayTip = (BmsTip) redisService.get("today_tip");
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
