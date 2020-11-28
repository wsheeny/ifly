package com.wcy.rhapsody.admin.service.api.impl;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {

    @Test
    void t() {

        String activeUrl = URLUtil.normalize("http://47.105.186.18" + "?user=mabaoguo&code=" + "1234124");
        String content = "请在30分钟内激活您的账号，如非本人操作，请忽略 </br > " +
                "<a href=\"" + activeUrl + "\" target =\"_blank\" '>点击激活账号</a>";
        MailUtil.send("1020317774@qq.com", "【滚雪球】账号激活", content, true);

    }
}