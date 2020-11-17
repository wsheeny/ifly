package com.wcy.rhapsody.admin.modules.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TipsVO
 *
 * @author Yeeep 2020/11/7
 */
@Data
@ApiModel("每日赠言")
public class TipsVO {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("1：使用，0：过期")
    private Boolean type;

    public TipsVO() {
    }
}