package com.wcy.rhapsody.admin.core;

/**
 * HttpStatus 状态码
 *
 * @author Yeeep
 * @see java.net.HttpURLConnection
 */
public class MyHttpCode {

    /* 2XX: generally "OK" */
    /**
     * 请求成功
     */
    public static final int OK = 200;

    /**
     * 50008: 非法Token
     */
    public static final int ILLEGAL_TOKEN = 50008;
    /**
     * Token失效
     */
    public static final int FAILURE_TOKEN = 50014;
    /**
     * 50012: 异地登录;
     */
    public static final int REMOTE_LOGIN = 50012;


    /* 3XX: relocation/redirect */

    /* 4XX: client error */

    /**
     * 未授权
     */
    public static final int HTTP_UNAUTHORIZED = 401;

    /**
     * 找不到
     */
    public static final int HTTP_NOT_FOUND = 404;

    /**
     * 服务器错误
     */
    public static final int HTTP_INTERNAL_ERROR = 500;

    /**
     * 用户已存在
     */
    public static final int USER_ALREADY_EXISTS = 10000;
    /**
     * 用户名错误
     */
    public static final int USER_NAME_ERROR = 10001;
    /**
     * 用户密码错误
     */
    public static final int USER_PASS_ERROR = 10002;
    /**
     * 用户名或密码错误
     */
    public static final int USER_NAME_PASS_ERROR = 10003;
    /**
     * 未关注
     */
    public static final int UN_FOLLOW = 20004;
    /**
     * 已关注
     */
    public static final int HAS_FOLLOW = 20005;

}
