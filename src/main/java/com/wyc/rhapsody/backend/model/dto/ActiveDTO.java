package com.wyc.rhapsody.backend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 账号激活DTO
 *
 * @author Knox 2020/11/26
 */
@Data
public class ActiveDTO {
    /**
     * 激活用户名
     */
    @NotBlank(message = "账号不能为空")
    private String user;

    /**
     * 激活码
     */
    @NotBlank(message = "激活码不能为空")
    private String code;

}
