package com.wyc.elegant.admin.config.shiro;

import cn.hutool.crypto.digest.BCrypt;
import org.junit.jupiter.api.Test;

class MyCredentialsMatcherTest {

    @Test
    void test() {
        String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(hashpw);

        boolean checkpw = BCrypt.checkpw("123456", hashpw);

        System.out.println(checkpw);
    }
}