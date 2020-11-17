package com.wcy.rhapsody.admin.modules.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * 用户注册DTO
 *
 * @author Yeeep 2020/11/12
 */
@Data
public class UserRegisterDTO {
    private String name;

    private String pass;

    private String checkPass;

    @Email
    @NotNull(message = "邮箱不可以为空")
    private String email;
}
