package com.knox.aurora.model.vo;

import com.knox.aurora.model.entity.BmsColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 专栏
 *
 * @author Knox
 * @date 2020/11/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BmsColumnVO extends BmsColumn {

    /**
     * 话题统计
     */
    private Integer topics;

    /**
     * 关注数
     */
    private Integer followers;

}
