package com.epam.aa.sportplace.listener;

import com.epam.aa.sportplace.dao.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartAppListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(StartAppListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initDaoFactory();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //TODO: how to close datasource here?
    }

    private void initDaoFactory() {
        String impl = ConfigurationProperties.getProperty("dao.factory");
        DaoFactory.init(impl);
        logger.info("DaoFactory is successfully initialized with implementation {}", impl);
    }
}