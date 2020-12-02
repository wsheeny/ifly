package com.wyc.elegant.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.elegant.admin.model.entity.Column;
import com.wyc.elegant.admin.model.vo.ColumnVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yeeep
 * @date 2020/11/28
 */
@Mapper
public interface ColumnMapper extends BaseMapper<Column> {

    /**
     * 查询专栏列表
     *
     * @param page
     * @return
     */
    Page<ColumnVO> selectPageVo(IPage<ColumnVO> page);
}
