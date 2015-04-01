package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.listener.ConfigurationException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.net.URL;

public class JdbcConfig {
    private static final Logger logger = LoggerFactory.getLogger(JdbcConfig.class);

    private static final String HIKARI_PROPERTIES_FILE = "/hikari.properties";

    public static String getHikariPropertiesPath() {
        URL resource = JdbcConfig.class.getResource(HIKARI_PROPERTIES_FILE);
        if (resource == null) {
            ConfigurationException configurationException = new ConfigurationException(
                    "ConfigurationProperties file '" + HIKARI_PROPERTIES_FILE + "' is missing in classpath.");
            logger.error(configurationException.getMessage(), configurationException);
            throw configurationException;
        }
        return resource.getPath();
    }

    public static void initJdbcDaoFactory() {
        //configure ikari, fetch databse and assign it to JdbcDaoFactory
        HikariConfig config = new HikariConfig(getHikariPropertiesPath());
        DataSource ds = new HikariDataSource(config);
        JdbcDaoFactory.setDataSource(ds);

        //Intstantiate Flyway with given
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.migrate();
    }
}
