package com.wcy.rhapsody.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcy.rhapsody.admin.model.entity.Column;
import com.wcy.rhapsody.admin.model.vo.ColumnVO;
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
