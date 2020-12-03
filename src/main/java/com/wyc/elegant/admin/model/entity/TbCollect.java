package com.wyc.elegant.admin.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏
 *
 * @author Knox
 */
@Data
@TableName("tb_collect")
@Accessors(chain = true)
public class TbCollect implements Serializable {

    private static final long serialVersionUID = 7610730966340643542L;

    private String topicId;

    @TableField("user_id")
    private String userId;

    @TableField(value = "create_Time", fill = FieldFill.INSERT)
    private Date createTime;
}
