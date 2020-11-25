// package com.wcy.rhapsody.admin.config.mybatisplus;
//
// import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.ibatis.reflection.MetaObject;
// import org.springframework.stereotype.Component;
//
// import java.time.LocalDateTime;
// import java.util.Date;
//
// /**
//  * Mybatis-plus自动填充
//  *
//  * @author Yeeep
//  */
// @Slf4j
// @Component
// public class MyMetaObjectHandler implements MetaObjectHandler {
//
//     @Override
//     public void insertFill(MetaObject metaObject) {
//         log.info("start insert fill ....");
//         this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
//     }
//
//     @Override
//     public void updateFill(MetaObject metaObject) {
//         log.info("start update fill ....");
//         // this.strictUpdateFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
//         this.strictUpdateFill(metaObject, "modifyTime", Date.class, new Date());
//     }
// }