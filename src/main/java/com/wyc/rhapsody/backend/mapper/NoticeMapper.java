package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyc.rhapsody.backend.model.entity.TbNotice;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 公告
 *
 * @author Knox 2020/11/19
 */
@Mapper
@Repository
public interface NoticeMapper extends BaseMapper<TbNotice> {
}
