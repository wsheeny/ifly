package com.wyc.elegant.admin.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台权限用户
 *
 * @author Yeeep 2020/11/7
 */
@Data
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 角色
     */
    private Integer roleId;

    /**
     * 账号状态：1：正常使用，0：已关闭
     */
    private Boolean status;

}
