package com.wyc.rhapsody.backend.exception;

import com.wyc.rhapsody.backend.common.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 统一异常处理
 *
 * @author Knox
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常捕获
     */
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        logger.error("全局异常：" + ExceptionUtil.getMessage(e));
        return R.error().message(e.getMessage());
    }

    /**
     * 处理JSR-303参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String defaultMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        logger.error("参数验证异常：" + defaultMessage);
        return R.error().message(defaultMessage);
    }

    /**
     * 处理自定义异常
     *
     * @param e 异常
     * @return {@link R}
     */
    @ExceptionHandler(value = MyException.class)
    public R myError(MyException e) {
        logger.error("自定义异常：" + ExceptionUtil.getMessage(e));
        return R.error().code(e.getCode()).message(e.getMessage());
    }
}
