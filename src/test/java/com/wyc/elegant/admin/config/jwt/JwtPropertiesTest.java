package com.wyc.elegant.admin.config.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtPropertiesTest {
    @Autowired
    private JwtProperties jwtProperties;

    @Test
    void test() {
        System.out.println(jwtProperties.toString());
    }
}