package com.knox.aurora.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.knox.aurora.model.entity.BmsColumn;
import com.knox.aurora.model.vo.BmsColumnVO;
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
