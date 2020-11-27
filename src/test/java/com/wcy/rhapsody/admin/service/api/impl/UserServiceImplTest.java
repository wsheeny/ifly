package com.wcy.rhapsody.admin.service.api.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcy.rhapsody.admin.modules.entity.web.TbSystemConfig;
import com.wcy.rhapsody.admin.service.SystemConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class UserServiceImplTest {

    @Test
    void t() {
        ThreadUtil.execAsync(() -> {
            // 发送激活邮件?user=hhh&code=true
            String activeUrl = URLUtil.normalize("http://47.105.186.18" + "?user=mabaoguo&code=" + "1234124");
            String content = "请在30分钟内激活您的账号，如非本人操作，请忽略 </br > " +
                    "<a href=\"" + activeUrl + "\" target =\"_blank\" '>点击激活账号</a>";
            MailUtil.send("1020317774@qq.com", "【滚雪球】账号激活", content, true);
        });
    }
}