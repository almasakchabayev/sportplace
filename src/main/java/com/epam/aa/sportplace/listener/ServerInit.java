package com.epam.aa.sportplace.listener;

import com.epam.aa.sportplace.dao.DAOFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class ServerInit implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(ServerInit.class);

    private HikariDataSource ds;
    {
        HikariConfig config = new HikariConfig(this.getClass().getResource("/hikari.properties").getPath());
        ds = new HikariDataSource(config);
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initFlyway();
        initDAOFactory();
    }

//    TODO: close something?
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

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
            throw new ServerInitException(e);
        }
        if (daoProps != null) {
            String impl = daoProps.getProperty("dao.factory");
            DAOFactory.init(impl, ds);
            return;
        }
        //TODO: is it ok? or just throw without logger would be better
        ServerInitException serverInitException = new ServerInitException("Could not initialise DaoFactory");
        logger.error("serverInitException", serverInitException);
        throw serverInitException;
    }
}
