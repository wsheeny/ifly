package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.rhapsody.backend.model.entity.TbColumn;
import com.wyc.rhapsody.backend.model.vo.TbColumnVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Knox
 * @date 2020/11/28
 */
@Mapper
@Repository
public interface ColumnMapper extends BaseMapper<TbColumn> {

    /**
     * 查询专栏列表
     *
     * @param page
     * @return
     */
    Page<TbColumnVO> selectPageVo(IPage<TbColumnVO> page);
}
