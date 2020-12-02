package com.wyc.elegant.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
// @Document(indexName = "topic", shards = 1, replicas = 1)
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    // @Id
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 标题
     */
    // @Field(analyzer = "ik_max_word", type = FieldType.Text)
    @NotBlank(message = "标题不可以为空")
    @TableField(value = "title")
    private String title;
    /**
     * markdown
     */
    // @Field(analyzer = "ik_max_word", type = FieldType.Text)
    @NotBlank(message = "内容不可以为空")
    @TableField("`content`")
    private String content;

    /**
     * 作者ID
     */
    // @Field(type = FieldType.Keyword)
    @TableField("user_id")
    private String userId;

    /**
     * 评论数
     */
    // @Field(type = FieldType.Integer)
    @TableField("comments")
    @Builder.Default
    private Integer comments = 0;

    /**
     * 收藏数
     */
    @TableField("collects")
    @Builder.Default
    // @Field(type = FieldType.Integer)
    private Integer collects = 0;

    /**
     * 浏览数
     */
    @TableField("view")
    @Builder.Default
    // @Field(type = FieldType.Integer)
    private Integer view = 0;

    /**
     * 所属分类
     */
    // @Field(type = FieldType.Keyword)
    @TableField("category_id")
    private String categoryId;

    /**
     * 专栏ID，默认不分栏
     */
    // @Field(type = FieldType.Integer)
    @TableField("section_id")
    @Builder.Default
    private Integer sectionId = 0;

    /**
     * 置顶
     */
    // @Field(type = FieldType.Boolean)
    @TableField("top")
    @Builder.Default
    private Boolean top = false;

    /**
     * 加精
     */
    // @Field(type = FieldType.Boolean)
    @TableField("essence")
    @Builder.Default
    private Boolean essence = false;

    /**
     * 创建时间
     */
    // @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    // @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;
}
