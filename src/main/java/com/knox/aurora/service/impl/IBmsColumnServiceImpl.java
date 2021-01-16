package com.knox.aurora.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knox.aurora.mapper.BmsColumnMapper;
import com.knox.aurora.model.entity.BmsColumn;
import com.knox.aurora.model.vo.BmsColumnVO;
import com.knox.aurora.service.IBmsColumnService;
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
