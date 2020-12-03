package com.wyc.elegant.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 系统启动器
 *
 * @author Knox
 */
@Slf4j
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.wyc")
public class ElegantAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElegantAdminApplication.class, args);
        log.info("System started successfully：http://127.0.0.1:10000 ⚡");
        log.info("Swagger document address：http://127.0.0.1:10000/swagger-ui.html 📕");
    }

}
