package com.wyc.elegant.admin;

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
    }
}
