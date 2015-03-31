package com.epam.aa.sportplace.listener;

import com.epam.aa.sportplace.dao.DaoFactory;
import com.epam.aa.sportplace.dao.JdbcDaoFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(AppListener.class);

    private HikariDataSource ds;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initDs();
        initFlyway();
        initDaoFactory();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ds.close();
    }

    private void initDs() {
        HikariConfig config = new HikariConfig(ConfigurationProperties.getHikariPropertiesPath());
        ds = new HikariDataSource(config);
        JdbcDaoFactory.setDataSource(ds);
    }

    private void initFlyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.migrate();
    }

    private void initDaoFactory() {
        String impl = ConfigurationProperties.getProperty("dao.factory");
        DaoFactory.init(impl);
        logger.info("Successfully initialized Dao Factory to {}", impl);
    }
}
