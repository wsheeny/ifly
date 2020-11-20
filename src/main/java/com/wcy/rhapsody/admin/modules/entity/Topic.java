package com.wcy.rhapsody.admin.modules.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 话题
 *
 * @author Yeeep
 */
@TableName("topic")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public @Data
@Builder
class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;
    /**
     * markdown
     */
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
     * 点赞用户的id英文,隔开的，要计算被多少人点赞过，可以通过英文,分隔这个字符串计算数量
     */
    @TableField("up_ids")
    private String upIds;

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
