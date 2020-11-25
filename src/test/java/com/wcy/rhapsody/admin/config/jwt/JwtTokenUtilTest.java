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


        String iwillbebetter = Jwts.parser()
                .setSigningKey("IWILLBEBETTER")
                .parseClaimsJws("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJXb25nWWlDaGVuIiwiaWF0IjoxNjA2MjQ3NDU1LCJzdWIiOiJhZG1pbiIsImV4cCI6MTYwNjI1NDY1NX0.Ta6VkewbyGKjT_5xO9h49E-dA7Q_pX-dra3heWzmglhTpsodM-8h9DxHUO1LD-_HJ6uaY60MtFOBNe67jHmPuA")
                .getBody().getSubject();


        System.out.println(iwillbebetter);
    }

}