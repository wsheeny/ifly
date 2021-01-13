package com.wyc.rhapsody.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.rhapsody.backend.model.entity.TbColumn;
import com.wyc.rhapsody.backend.model.entity.TbPost;
import com.wyc.rhapsody.backend.model.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 话题，议题
 *
 * @author Knox 2020/11/7
 */
@Mapper
@Repository
public interface TopicMapper extends BaseMapper<TbPost> {
    /**
     * 分页查询首页话题列表
     * <p>
     * // TODO: 2020/11/7 SQL 优化
     *
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> selectListAndPage(@Param("page") Page<PostVO> page, @Param("tab") String tab);

    /**
     * 获取详情页推荐
     *
     * @param id
     * @return
     */
    List<TbPost> selectRecommend(@Param("id") String id);

    /**
     * 专栏检索
     *
     * @param page
     * @param column
     * @return
     */
    Page<PostVO> selectByColumn(@Param("page") Page<PostVO> page, @Param("column") TbColumn column);

    /**
     * 全文检索
     *
     * @param page
     * @param keyword
     * @return
     */
    Page<PostVO> searchByKey(@Param("page") Page<PostVO> page, @Param("keyword") String keyword);
}
