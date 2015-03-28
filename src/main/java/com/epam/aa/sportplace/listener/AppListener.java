package com.epam.aa.sportplace.listener;

import com.epam.aa.sportplace.dao.DAOFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class AppListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(AppListener.class);

    private HikariDataSource ds;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initDs();
        initFlyway();
        initDAOFactory();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ds.close();
    }

    private void initDs() {
        HikariConfig config = new HikariConfig(this.getClass().getResource("/hikari.properties").getPath());
        ds = new HikariDataSource(config);
    }

    private void initFlyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.migrate();
    }

    private void initDAOFactory() {
        Properties daoProps = null;
        InputStream in;
        try {
            daoProps = new Properties();
            in = this.getClass().getResourceAsStream("/dao.properties");
            daoProps.load(in);
            in.close();
        } catch (IOException e) {
            logger.error("input stream could not be loaded to properties object or closed", e);
            throw new AppListenerException(e);
        }
        if (daoProps == null) {
            AppListenerException appListenerException = new AppListenerException("Could not initialise DaoFactory");
            logger.error(appListenerException.getMessage(), appListenerException);
            throw appListenerException;
        }
        String impl = daoProps.getProperty("dao.factory");
        DAOFactory.init(impl, ds);
    }
}
