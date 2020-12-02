package com.wcy.rhapsody.admin.exception;

import com.wcy.rhapsody.admin.common.MyHttpCode;
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
public class TokenException extends RuntimeException {
    private Integer code;

    private String message;

    public TokenException() {
        this.message = "无效的Token";
        this.code = MyHttpCode.FAILURE_TOKEN;
    }

    public TokenException code(Integer code) {
        this.code = code;
        return this;
    }

    public TokenException message(String message) {
        this.message = message;
        return this;
    }
}
