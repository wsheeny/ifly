package com.wcy.rhapsody.admin.exception;

import com.wcy.rhapsody.admin.core.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author Yeeep
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 错误页面统一处理,如断言
     *
     * @param e 异常
     * @return {@link R}
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        logger.error("异常信息：" + ExceptionUtil.getMessage(e));
        return R.error().message(e.getMessage());
    }

    /**
     * 处理自定义异常
     *
     * @param e 异常
     * @return {@link R}
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public R myError(MyException e) {
        logger.error("异常信息：" + ExceptionUtil.getMessage(e));
        return R.error().code(e.getCode()).message(e.getMessage());
    }
}
