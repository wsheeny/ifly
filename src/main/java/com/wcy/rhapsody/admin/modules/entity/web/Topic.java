package com.wcy.rhapsody.admin.modules.entity.web;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 话题
 *
 * @author Yeeep
 */
@Data
@Builder
@TableName("topic")
@AllArgsConstructor
@NoArgsConstructor
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 标题
     */
    @NotBlank(message = "标题不可以为空")
    @TableField(value = "title")
    private String title;
    /**
     * markdown
     */
    @NotBlank(message = "内容不可以为空")
    @TableField("`content`")
    private String content;

    /**
     * 作者ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 评论数
     */
    @TableField("comments")
    @Builder.Default
    private Integer comments = 0;

    /**
     * 收藏数
     */
    @TableField("collects")
    @Builder.Default
    private Integer collects = 0;

    /**
     * 浏览数
     */
    @TableField("view")
    @Builder.Default
    private Integer view = 0;

    /**
     * 所属分类
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 置顶
     */
    @TableField("top")
    @Builder.Default
    private Boolean top = false;

    /**
     * 加精
     */
    @TableField("essence")
    @Builder.Default
    private Boolean essence = false;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;
}
