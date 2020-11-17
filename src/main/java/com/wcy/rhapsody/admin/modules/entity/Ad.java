package com.wcy.rhapsody.admin.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 广告
 *
 * @author Yeeep
 * @create 2020/10/20
 */
@Data
@TableName("ad")
@Accessors(chain = true)
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告标题
     */
    @TableField("title")
    private String title;

    /**
     * 广告链接
     */
    @TableField("link")
    private String link;

    /**
     * 说明
     */
    @TableField("`description`")
    private String description;

    public Ad() {
    }

}