package com.wyc.rhapsody.backend.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * Tag管理主题
 *
 * @author Knox 2020/11/14
 */
@Data
public class TagTopicsVO {

    private String id;

    private String alias;

    private String avatar;

    private String title;

    private Date createTime;

    private Integer view;

    private Integer comments;


}
