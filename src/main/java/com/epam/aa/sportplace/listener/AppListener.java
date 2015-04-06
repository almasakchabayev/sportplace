package com.epam.aa.sportplace.listener;

import com.epam.aa.sportplace.dao.DaoFactory;
import com.epam.aa.sportplace.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(AppListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initDaoFactory();
        Validator.init();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //TODO: how to close datasource here?
    }

    private void initDaoFactory() {
        String impl = DaoProperties.getProperty("dao.factory");
        DaoFactory.init(impl);
        logger.info("DaoFactory is successfully initialized with implementation {}", impl);
    }
}