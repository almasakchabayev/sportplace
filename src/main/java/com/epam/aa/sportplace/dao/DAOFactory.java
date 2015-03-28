package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public abstract class DAOFactory {
    private static final Logger logger = LoggerFactory.getLogger(JdbcDAOFactory.class);

    private static String impl;

    private Connection connection;

    public static void init(String implProp) {
        if (implProp == null || implProp.isEmpty()) {
            DAOException daoException = new DAOException("dao.factory property is not defined in dao.properties file");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        impl = implProp;
    }

    public static DAOFactory getFactory() {
        if (impl == null) {
            DAOException daoException = new DAOException("DAOFactory was not initilized");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        if (impl.equals("jdbc")) {
            return new JdbcDAOFactory();
        }
        DAOException daoException = new DAOException("no Factories found for impl provided");
        logger.error(daoException.getMessage(), daoException);
        throw daoException;
    }

    public DAOFactory() {
    }

    public abstract CustomerDAO getCustomerDAO();

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}