package com.wcy.rhapsody.admin.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.config.redis.RedisService;
import com.wcy.rhapsody.admin.mapper.api.TipMapper;
import com.wcy.rhapsody.admin.model.entity.web.Tip;
import com.wcy.rhapsody.admin.service.api.TipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Tip实现类
 *
 * @author Yeeep 2020/11/7
 */
@Slf4j
@Service
public class TipServiceImpl extends ServiceImpl<TipMapper, Tip> implements TipService {

    @Resource
    private RedisService redisService;

    /**
     * 每日赠言，一天的时效
     *
     * @param type 类型
     * @return
     */
    @Override
    public Tip getRandomTip(Integer type) {
        Tip todayTip = null;
        try {
            todayTip = (Tip) redisService.get("today_tip");
            if (StringUtils.isEmpty(todayTip)) {
                todayTip = this.baseMapper.getRandomTip(type);
                redisService.set("today_tip", todayTip, 24 * 60 * 60);
            }
        } catch (Exception e) {
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
