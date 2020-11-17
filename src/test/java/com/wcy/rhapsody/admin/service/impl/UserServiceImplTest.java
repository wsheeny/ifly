package com.wcy.rhapsody.admin.service.impl;

import cn.hutool.core.util.URLUtil;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {

    @Test
    void t() {
        String activeUrl = URLUtil.normalize("localhost:9999/user/active");
        String content = "请在30分钟内激活您的账号，如非本人操作，请忽略 </br > " +
                "<a href=\"" + activeUrl + "\" target =\"_blank\" '>点击激活账号</a>";

        System.out.println(content);
    }
}