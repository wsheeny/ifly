package com.wcy.rhapsody.admin.model.entity.web;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
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
@Builder
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

    @Builder.Default
    @TableField("avatar")
    private String avatar = "https://s3.ax1x.com/2020/11/12/BxJaSs.jpg";

    @TableField("email")
    private String email;

    @Builder.Default
    @TableField("mobile")
    private String mobile;

    @Builder.Default
    @TableField("bio")
    private String bio = "自由职业者";

    @Builder.Default
    @TableField("signature")
    private String signature = "这个人很懒，什么都没有留下";

    @Builder.Default
    @TableField("score")
    private Integer score = 0;

    @JsonIgnore
    @TableField("token")
    private String token;

    @Builder.Default
    @TableField("active")
    private Boolean active = false;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
}
