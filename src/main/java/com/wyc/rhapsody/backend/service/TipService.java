package com.wyc.rhapsody.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.rhapsody.backend.model.entity.TbTip;

/**
 * Tip
 *
 * @author Knox 2020/11/7
 */
public interface TipService extends IService<TbTip> {
    /**
     * 获取每日赠言
     *
     * @param type 类型
     * @return {@link TbTip}
     */
    TbTip getRandomTip(Integer type);
}
