package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.amor.model.entity.BmsColumn;
import com.wyc.amor.model.vo.BmsColumnVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Knox
 * @date 2020/11/28
 */
@Mapper
@Repository
public interface BmsColumnMapper extends BaseMapper<BmsColumn> {

    /**
     * 查询专栏列表
     *
     * @param page
     * @return
     */
    Page<BmsColumnVO> selectPageVo(IPage<BmsColumnVO> page);
}
