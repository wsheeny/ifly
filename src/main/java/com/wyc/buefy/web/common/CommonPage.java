package com.wyc.buefy.web.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页数据封装类
 * <p>
 * 对mybatis-Plus和Spring-data-elasticsearch重新封装
 *
 * @author macro
 * @date 2019/4/19
 */
@Data
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    // /**
    //  * 将PageHelper分页后的list转为分页信息
    //  */
    // public static <T> CommonPage<T> restPage(List<T> list) {
    //     CommonPage<T> result = new CommonPage<T>();
    //
    //     PageInfo<T> pageInfo = new PageInfo<T>(list);
    //     result.setTotalPage(pageInfo.getPages());
    //     result.setPageNum(pageInfo.getPageNum());
    //     result.setPageSize(pageInfo.getPageSize());
    //     result.setTotal(pageInfo.getTotal());
    //     result.setList(pageInfo.getList());
    //     return result;
    // }

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(pageInfo.getTotalPages());
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotalElements());
        result.setList(pageInfo.getContent());
        return result;
    }
}
