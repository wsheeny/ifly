package com.wyc.buefy.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 前端后台
 * <p>
 * 注：前端后台与后端后台不共用
 *
 * @author Knox
 */
@Slf4j
@EnableCaching
@MapperScan("com.wyc.buefy.web.mapper")
@SpringBootApplication()
public class BuefyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuefyWebApplication.class, args);
        log.info("server_url-------------->http://127.0.0.1:10000 ⚡");
        log.info("server_api-------------->http://127.0.0.1:10000/swagger-ui.html 📕");
    }

}
