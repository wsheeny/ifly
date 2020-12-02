package com.wyc.elegant.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.elegant.admin.model.entity.Column;
import com.wyc.elegant.admin.model.vo.ColumnVO;

/**
 * 用户专栏
 *
 * @author Yeeep
 * @date 2020/11/28
 */
public interface ColumnService extends IService<Column> {

    /**
     * 查询专栏列表
     *
     * @param columnVOPage
     * @return
     */
    Page<ColumnVO> getList(Page<ColumnVO> columnVOPage);
}
