package com.wcy.rhapsody.admin.modules.vo;

import com.wcy.rhapsody.admin.modules.entity.web.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 话题视图层
 *
 * @author Yeeep 2020/11/7
 */
@Data
@ApiModel("首页话题列表")
public class TopicVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    @ApiModelProperty("文章ID")
    private String id;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private String userId;
    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;
    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String alias;
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String username;
    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 评论统计
     */
    @ApiModelProperty("评论统计")
    private Integer comments;
    /**
     * 置顶
     */
    @ApiModelProperty("置顶")
    private Boolean top;
    /**
     * 加精
     */
    @ApiModelProperty("加精")
    private Boolean essence;
    /**
     * 收藏次數
     */
    @ApiModelProperty("收藏次數")
    private Integer collects;
    /**
     * 话题关联标签
     */
    @ApiModelProperty("话题关联标签")
    private List<Tag> tags;
    /**
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private Integer view;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date modifyTime;
}
