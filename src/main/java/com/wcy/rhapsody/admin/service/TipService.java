package com.wcy.rhapsody.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcy.rhapsody.admin.modules.entity.Tips;

/**
 * Tip
 *
 * @author Yeeep 2020/11/7
 */
public interface TipService extends IService<Tips> {
    /**
     * 获取每日赠言
     *
     * @param type 类型
     * @return {@link Tips}
     */
    Tips getRandomTip(Integer type);
}
