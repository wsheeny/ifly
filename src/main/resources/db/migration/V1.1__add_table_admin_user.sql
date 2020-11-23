CREATE TABLE `admin_user` (
                              `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
                              `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `role_id` int NOT NULL COMMENT '角色',
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE KEY `username` (`username`) USING BTREE,
                              KEY `role_id` (`role_id`) USING BTREE,
                              CONSTRAINT `admin_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='后台系统用户';