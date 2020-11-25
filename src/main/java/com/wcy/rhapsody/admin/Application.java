package com.wcy.rhapsody.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * rhapsody-为极客而生！
 *
 * @author Yeeep
 */
@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.wcy", exclude = FlywayAutoConfiguration.class)
public class Application {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        logger.info("启动成功：http://127.0.0.1:" + run.getEnvironment().getProperty("server.port") + "⚡");
        logger.info("接口文档：http://127.0.0.1:" + run.getEnvironment().getProperty("server.port") + "/swagger-ui.html#/⚡");
    }

}
