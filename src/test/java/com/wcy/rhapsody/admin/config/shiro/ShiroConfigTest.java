package com.wcy.rhapsody.admin.config.shiro;

import org.apache.shiro.codec.Base64;
import org.junit.jupiter.api.Test;

class ShiroConfigTest {

    @Test
    void testLength() {
        byte[] bytes = "i will be better".getBytes();
        byte[] cipherKey = Base64.encode(("Yeeepisthebest!!").getBytes());
        System.out.println(bytes.length);
        System.out.println(cipherKey.length);
    }
}