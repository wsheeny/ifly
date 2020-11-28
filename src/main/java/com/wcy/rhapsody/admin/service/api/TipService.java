package com.wcy.rhapsody.admin.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcy.rhapsody.admin.model.entity.web.Tip;

/**
 * Tip
 *
 * @author Yeeep 2020/11/7
 */
public interface TipService extends IService<Tip> {
    /**
     * 获取每日赠言
     *
     * @param type 类型
     * @return {@link Tip}
     */
    Tip getRandomTip(Integer type);
}
