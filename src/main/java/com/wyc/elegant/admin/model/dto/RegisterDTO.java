package com.wyc.elegant.admin.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 用户注册DTO
 *
 * @author Yeeep 2020/11/12
 */
@Data
public class RegisterDTO {

    @NotEmpty(message = "用户不能为空")
    @Length(min = 2, max = 15, message = "长度在2-15")
    private String name;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "长度在6-20")
    private String pass;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "长度在6-20")
    private String checkPass;

    @NotEmpty(message = "邮箱不可以为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}
