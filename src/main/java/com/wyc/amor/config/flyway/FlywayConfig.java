package com.wyc.amor.config.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Flyway数据库版本控制配置
 *
 * @author Knox
 */
@Configuration
public class FlywayConfig {
    @Resource
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
    }
}
