package com.knox.aurora.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * CommandLineRunner
 *
 * @author Knox
 */
@Component
public class ServerRunner implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(ServerRunner.class);

    @Override
    public void run(String... args) throws Exception {
        // 打开浏览器
        log.info("Server：http://127.0.0.1:8000 ⚡");
        log.info("ApiDoc：http://127.0.0.1:8000/swagger-ui.html 📕");
    }
}
