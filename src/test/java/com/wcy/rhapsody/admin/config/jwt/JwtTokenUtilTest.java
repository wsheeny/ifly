package com.wcy.rhapsody.admin.config.jwt;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.util.Date;

class JwtTokenUtilTest {

    @Test
    void e() {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuer("WongYiChen")
                .setIssuedAt(new Date())
                .setSubject("WebToken")
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 1000))
                .signWith(SignatureAlgorithm.HS256, "WongYiChen");

        /*JWS*/
        String jws = jwtBuilder.compact();
        System.out.println(jws);
        Jwts.parser().setSigningKey("WongYiChen");

        Jws<Claims> wongYiChen = Jwts.parser().setSigningKey("WongYiChen").parseClaimsJws(jws);
        System.out.println(wongYiChen);
        System.out.println(wongYiChen.getBody());

    }

}