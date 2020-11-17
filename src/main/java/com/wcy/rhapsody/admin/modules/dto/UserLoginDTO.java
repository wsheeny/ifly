package com.wcy.rhapsody.admin.modules.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户登录
 *
 * @author Yeeep 2020/11/7
 */
@Data
@ApiModel(value = "前台用户登录请求对象")
public class UserLoginDTO {

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不可以为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不可以为空")
    private String password;

    @ApiModelProperty(value = "记住我")
    private Boolean rememberMe;

    public UserLoginDTO() {
    }
}
