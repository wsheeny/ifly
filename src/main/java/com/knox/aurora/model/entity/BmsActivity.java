package com.knox.aurora.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动
 *
 * @author Knox 2020/11/21
 */
@Data
@TableName("bms_activity")
public class BmsActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 活动标题
     */
    @TableField("`title`")
    private String title;

    /**
     * 活动banner
     */
    @TableField("`thumbnail`")
    private String thumbnail;

    /**
     * 活动时间
     */
    @TableField("time")
    private Date time;

    /**
     * 活动位置
     */
    @TableField("`location`")
    private String location;

    /**
     * 活动链接
     */
    @TableField("`link`")
    private String link;

    /**
     * 活动是否结束，1：未结束，0：已结束
     */
    @TableField("`status`")
    private Boolean status;

    public BmsActivity() {
    }

}
