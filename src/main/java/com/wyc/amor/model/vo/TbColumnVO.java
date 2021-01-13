package com.wyc.amor.model.vo;

import com.wyc.amor.model.entity.TbColumn;
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
public class TbColumnVO extends TbColumn {

    /**
     * 话题统计
     */
    private Integer topics;

    /**
     * 关注数
     */
    private Integer followers;

}
