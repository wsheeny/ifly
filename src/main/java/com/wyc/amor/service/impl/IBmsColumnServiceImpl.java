package com.wyc.amor.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.amor.mapper.BmsColumnMapper;
import com.wyc.amor.model.entity.BmsColumn;
import com.wyc.amor.model.vo.BmsColumnVO;
import com.wyc.amor.service.IBmsColumnService;
import org.springframework.stereotype.Service;

/**
 * @author Knox
 * @date 2020/11/28
 */
@Service
public class IBmsColumnServiceImpl extends ServiceImpl<BmsColumnMapper, BmsColumn> implements IBmsColumnService {

    @Override
    public Page<BmsColumnVO> getList(Page<BmsColumnVO> page) {
        return this.baseMapper.selectPageVo(page);
    }
}
