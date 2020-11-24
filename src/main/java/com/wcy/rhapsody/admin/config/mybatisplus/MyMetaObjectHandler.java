package com.wcy.rhapsody.admin.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Mybatis-plus自动填充
 *
 * @author Yeeep
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        // this.strictUpdateFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "modifyTime", Date.class, new Date());
        // this.strictUpdateFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
    }
}
