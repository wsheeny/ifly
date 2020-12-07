package com.wyc.buefy.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyc.buefy.web.model.vo.TbColumnVO;
import com.wyc.buefy.web.model.entity.TbColumn;

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
