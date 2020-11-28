package com.wcy.rhapsody.admin.model.entity.web;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户话题专栏
 *
 * @author Yeeep
 * @date 2020/11/28
 */
@Data
@Builder
@TableName("section")
@NoArgsConstructor
@AllArgsConstructor
public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 专栏作者
     */
    @TableField("user_id")
    private String userId;

    /**
     * 专栏标题
     */
    @TableField("title")
    private String title;

    /**
     * 专栏描述
     */
    @Builder.Default
    @TableField("`description`")
    private String description = "当前专栏暂未添加说明";

    /**
     * 专栏图片
     */
    @TableField("thumbnail")
    private String thumbnail;
}