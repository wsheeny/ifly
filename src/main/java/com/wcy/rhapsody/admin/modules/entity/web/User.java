package com.wcy.rhapsody.admin.modules.entity.web;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author Yeeep
 */
@Data
@TableName("user")
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -5051120337175047163L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("username")
    private String username;

    @TableField("alias")
    private String alias;

    @JsonIgnore()
    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("email")
    private String email;

    @TableField("mobile")
    private String mobile;

    @TableField("bio")
    private String bio;

    @TableField("signature")
    private String signature;

    @JsonIgnore
    @TableField("token")
    private String token;

    @TableField("active")
    private Boolean active;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
}
