package com.wcy.rhapsody.admin.model.vo;

import lombok.Data;

/**
 * TipsVO
 *
 * @author Yeeep 2020/11/7
 */
@Data
public class TipsVO {

    private Integer id;

    private String content;

    private String author;

    private Boolean type;
}