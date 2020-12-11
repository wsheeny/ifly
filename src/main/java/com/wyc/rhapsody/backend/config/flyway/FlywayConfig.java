package com.wyc.rhapsody.backend.config.flyway;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * 管理数据库版本
 *
 * @author Knox
 */
@Configuration
public class FlywayConfig {
    private final Logger logger = LoggerFactory.getLogger(FlywayConfig.class);

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .encoding("UTF-8")
                .load();
        flyway.migrate();
        logger.info("Flyway数据库版本同步迁移成功");
    }
}
