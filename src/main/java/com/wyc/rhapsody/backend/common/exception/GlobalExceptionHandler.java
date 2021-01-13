package com.wyc.rhapsody.backend.common.exception;

import com.wyc.rhapsody.backend.common.api.ApiResult;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 全局异常处理
 *
 * @author knox
 * @since 2020/2/27
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获自定义异常
     */
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ApiResult<Map<String, Object>> handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return ApiResult.failed(e.getErrorCode());
        }
        return ApiResult.failed(e.getMessage());
    }

    /**
     * 参数验证无效异常【处理JSR-303参数校验异常】
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult<Map<String, Object>> handleValidException(MethodArgumentNotValidException e) {
        String message = null;
        if (e.getBindingResult().hasErrors()) {
            FieldError fieldError = e.getBindingResult().getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return ApiResult.validateFailed(message);
    }

    /**
     * bind异常
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ApiResult<Map<String, Object>> handleValidException(BindException e) {
        String message = null;
        if (e.getBindingResult().hasErrors()) {
            FieldError fieldError = e.getBindingResult().getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return ApiResult.validateFailed(message);
    }
}
