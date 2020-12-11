package com.wyc.rhapsody.backend.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * 自定义分页数据封装类
 * <p>
 * 对mybatis-Plus和Spring-data-elasticsearch重新封装
 *
 * @author knox
 */
@Data
public class MyPage<T> {
    private Long pageNum;
    private Long pageSize;
    private Long totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将Mybatis-Plus分页信息重新封装
     */
    public static <T> MyPage<T> myPage(Page<T> pageInfo) {
        MyPage<T> result = new MyPage<T>();
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getCurrent());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getRecords());
        return result;
    }
}
