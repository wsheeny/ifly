package com.wcy.rhapsody.admin.model.vo;

import com.wcy.rhapsody.admin.model.entity.web.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 专栏
 *
 * @author Yeeep
 * @date 2020/11/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ColumnVO extends Column {

    /**
     * 话题统计
     */
    private Integer topics;

    /**
     * 关注数
     */
    private Integer followers;

}
