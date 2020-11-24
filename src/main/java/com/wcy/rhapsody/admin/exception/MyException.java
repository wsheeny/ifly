package com.wcy.rhapsody.admin.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义接口异常，重写code，message
 *
 * @author Yeeep
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MyException extends RuntimeException {

    private Integer code;
    private String message;

    public MyException(String message) {
        this.code = 10000;
        this.message = message;
    }

    public MyException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
