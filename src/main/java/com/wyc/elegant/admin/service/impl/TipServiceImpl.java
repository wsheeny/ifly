package com.wyc.elegant.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.component.RedisService;
import com.wyc.elegant.admin.mapper.TipMapper;
import com.wyc.elegant.admin.model.entity.Tip;
import com.wyc.elegant.admin.service.TipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Tip实现类
 *
 * @author Yeeep 2020/11/7
 */
@Slf4j
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
