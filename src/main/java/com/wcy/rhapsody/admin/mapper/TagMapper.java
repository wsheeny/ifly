package com.wcy.rhapsody.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcy.rhapsody.admin.modules.entity.Tag;
import com.wcy.rhapsody.admin.modules.entity.Topic;
import com.wcy.rhapsody.admin.modules.vo.TagTopicsVO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * Tag
 *
 * @author Yeeep 2020/11/7
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
