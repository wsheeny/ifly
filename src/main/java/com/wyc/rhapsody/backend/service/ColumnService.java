package com.wyc.rhapsody.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.rhapsody.backend.model.entity.TbColumn;
import com.wyc.rhapsody.backend.model.vo.TbColumnVO;

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
