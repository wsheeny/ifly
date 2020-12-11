package com.wyc.rhapsody.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.rhapsody.backend.mapper.ColumnMapper;
import com.wyc.rhapsody.backend.model.entity.TbColumn;
import com.wyc.rhapsody.backend.model.vo.TbColumnVO;
import com.wyc.rhapsody.backend.service.ColumnService;
import org.springframework.stereotype.Service;

/**
 * @author Knox
 * @date 2020/11/28
 */
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, TbColumn> implements ColumnService {

    @Override
    public Page<TbColumnVO> getList(Page<TbColumnVO> page) {
        return this.baseMapper.selectPageVo(page);
    }
}
