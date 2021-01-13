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
 * 日常记录
 *
 * @author Knox 2020/11/17
 */
@Data
@Builder
@Accessors(chain = true)
@TableName("bms_daily")
@NoArgsConstructor
@AllArgsConstructor
public class BmsDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("img")
    private String img;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 位置
     */
    @TableField("location")
    private String location;

    /**
     * 发布时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

    /**
     * 点赞
     */
    @Builder.Default
    @TableField("`like`")
    private Integer like = 0;

}
