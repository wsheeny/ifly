package com.wcy.rhapsody.admin.modules.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录
 *
 * @author Yeeep 2020/11/7
 */
@Data
public class LoginDTO {

    @NotEmpty(message = "用户不能为空")
    @Length(min = 2, max = 15, message = "长度在2-15")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "长度在6-20")
    private String password;

    private Boolean rememberMe;

}
