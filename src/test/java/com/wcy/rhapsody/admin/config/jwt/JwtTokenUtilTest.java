package com.wcy.rhapsody.admin.config.jwt;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

class JwtTokenUtilTest {

    @Test
    void e() {
        String iwillbebetter = Jwts.parser()
                .setSigningKey("IWILLBEBETTER")
                .parseClaimsJws("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJXb25nWWlDaGVuIiwiaWF0IjoxNjA2NTUyOTg5LCJzdWIiOiJhZG1pbiIsImV4cCI6MTYwNjU2MDE4OX0.xzSG8hs_jTCo3ViaQvCaAVkttO12vujB29IXr2M0K_M")
                .getBody().getSubject();


        System.out.println(iwillbebetter);
    }

}