package com.knox.aurora.common.api;

/**
 * REST API 错误码接口
 *
 * @author knox
 * @since 2020/08/19
 */
public interface IErrorCode {
    /**
     * 错误编码: -1失败;200成功
     *
     * @return 错误编码
     */
    Integer getCode();

    /**
     * 错误描述
     *
     * @return 错误描述
     */
    String getMessage();
}
