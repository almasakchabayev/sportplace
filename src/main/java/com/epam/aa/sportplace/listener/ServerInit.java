package com.epam.aa.sportplace.listener;

import com.epam.aa.sportplace.dao.DAOFactory;
import org.flywaydb.core.Flyway;

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
    @Resource(name="jdbc/sportplacedb")
    private DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initDAOFactory();
        initFlyway();
    }

//    TODO: close something?
    @Override
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (daoProps != null) {
            String impl = daoProps.getProperty("dao.factory");
            DAOFactory.init(impl, ds);
        }
    }
}
