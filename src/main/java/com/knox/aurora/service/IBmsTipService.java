package com.knox.aurora.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knox.aurora.model.entity.BmsTip;

/**
 * Tip
 *
 * @author Knox 2020/11/7
 */
public interface IBmsTipService extends IService<BmsTip> {
    /**
     * 获取每日赠言
     *
     * @param type 类型
     * @return {@link BmsTip}
     */
    BmsTip getRandomTip(Integer type);
}
