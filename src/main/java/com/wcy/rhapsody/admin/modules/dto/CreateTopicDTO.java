package com.wcy.rhapsody.admin.modules.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 发布话题
 *
 * @author Yeeep 2020/11/14
 */
@Data
public class CreateTopicDTO implements Serializable {
    private static final long serialVersionUID = -5957433707110390852L;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 分类ID
     */
    private String categoryId;

}
