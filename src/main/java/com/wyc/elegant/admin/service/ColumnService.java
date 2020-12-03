package com.wyc.elegant.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.elegant.admin.model.entity.TbColumn;
import com.wyc.elegant.admin.model.vo.TbColumnVO;

/**
 * 用户专栏
 *
 * @author Knox
 * @date 2020/11/28
 */
public interface ColumnService extends IService<TbColumn> {

    /**
     * 查询专栏列表
     *
     * @param columnVOPage
     * @return
     */
    Page<TbColumnVO> getList(Page<TbColumnVO> columnVOPage);
}
