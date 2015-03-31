package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public abstract class DaoFactory {
    private static final Logger logger = LoggerFactory.getLogger(JdbcDaoFactory.class);

    private static String impl;

    private Connection connection;

    public static void init(String implProp) {
        if (implProp == null || implProp.isEmpty()) {
            DaoException daoException = new DaoException("dao.factory property is not defined in dao.properties file");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        impl = implProp;
    }

    public static DaoFactory getDAOFactory() {
        if (impl == null) {
            DaoException daoException = new DaoException("DaoFactory was not initilized");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        if (impl.equals("jdbc")) {
            return new JdbcDaoFactory();
        }
        DaoException daoException = new DaoException("no Factories found for impl provided");
        logger.error(daoException.getMessage(), daoException);
        throw daoException;
    }

    public DaoFactory() {
    }

    public abstract CustomerDao getCustomerDAO();

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}