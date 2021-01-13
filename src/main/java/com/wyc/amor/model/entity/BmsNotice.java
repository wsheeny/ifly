package com.wyc.amor.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告
 *
 * @author Knox 2020/11/19
 */
@Data
@Builder
@Accessors(chain = true)
@TableName("bms_notice")
@NoArgsConstructor
@AllArgsConstructor
public class BmsNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 公告
     */
    @TableField("content")
    private String content;

    /**
     * 公告时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 1：展示中，0：过期
     */
    @Builder.Default
    @TableField("`show`")
    private boolean show = false;

}
