// package com.wcy.rhapsody.admin.config.mybatisplus;
//
// import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
// import org.apache.ibatis.reflection.MetaObject;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
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
// @Component
// public class MyMetaObjectHandler implements MetaObjectHandler {
//     private final Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);
//
//     @Override
//     public void insertFill(MetaObject metaObject) {
//         logger.info("MP开始自动填充 | start insert fill ....");
//         // 起始版本 3.3.0(推荐使用)
//         this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
//         // this.strictUpdateFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
//     }
//
//     @Override
//     public void updateFill(MetaObject metaObject) {
//         logger.info("MP开始自动填充 | start update fill ....");
//         // 起始版本 3.3.0(推荐)
//         this.strictUpdateFill(metaObject, "modifyTime", Date.class, new Date());
//         // this.strictUpdateFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
//     }
// }
