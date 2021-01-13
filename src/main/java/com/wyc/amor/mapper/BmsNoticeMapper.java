package com.wyc.amor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.amor.model.entity.BmsNotice;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 公告
 *
 * @author Knox 2020/11/19
 */
@Mapper
@Repository
public interface BmsNoticeMapper extends BaseMapper<BmsNotice> {
}
