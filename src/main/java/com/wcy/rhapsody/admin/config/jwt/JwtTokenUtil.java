package com.wcy.rhapsody.admin.config.jwt;

import cn.hutool.core.date.DateUtil;
import com.wcy.rhapsody.admin.model.entity.web.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 *
 * @author Yeeep 2020/11/19
 */
@Component
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static final String SECRET = "IWILLBEBETTER";
    public static final int EXPIRE_DATE = 2;

    /**
     * 生成JWT-token
     *
     * @param userDetails 登录用户信息
     */
    public String generateToken(User userDetails) {

        Map<String, Object> header = new HashMap<>(16);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        /*这里是2小时失效*/
        Date expireDate = DateUtil.offsetHour(new Date(), EXPIRE_DATE);
        return Jwts.builder()
                .setHeader(header)
                .setIssuer("WongYiChen")
                .setIssuedAt(new Date())
                .setSubject(userDetails.getUsername())
                // 封装私有声明 claims ，也可以直接注册声明
                // .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                // 压缩成jws
                .compact();
    }

    /**
     * 解析Jwt对象（包括：header，body，signature）
     */
    public Claims parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            logger.info("Token失效，请重新登录");
        }
        return claims;
    }

    /**
     * 验证token是否还有效
     *
     * @param token  客户端传入的token
     * @param dbUser 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, User dbUser) {
        Claims claims = parseToken(token);
        // 用户名
        String username = claims.getSubject();
        // 获取过期时间
        Date expiration = claims.getExpiration();
        // 用户名一致，有效期内
        return username.equals(dbUser.getUsername()) && !expiration.before(new Date());
    }

    /**
     * 判断token是否可以被刷新
     */
    public String refreshToken(String token, User dbUser) {
        // 获取过期时间
        Date expiration = parseToken(token).getExpiration();
        if (expiration.before(new Date())) {
            return generateToken(dbUser);
        }
        return null;
    }
}