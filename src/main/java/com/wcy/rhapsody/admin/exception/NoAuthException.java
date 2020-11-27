package com.wcy.rhapsody.admin.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 未登录异常
 *
 * @author Yeeep
 * @date 2020/11/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NoAuthException extends RuntimeException {
    private Integer code;

    private String message;

    public NoAuthException() {
        this.message = "未登录";
        this.code = 401;
    }

    public NoAuthException code(Integer code) {
        this.code = code;
        return this;
    }

    public NoAuthException message(String message) {
        this.message = message;
        return this;
    }
}
