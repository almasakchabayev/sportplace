package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public abstract class DaoFactory1 {
    private static final Logger logger = LoggerFactory.getLogger(JdbcDaoFactory1.class);

    private static String impl;

    private Connection connection;

    public static void init(String implProp) {
        if (implProp == null || implProp.isEmpty()) {
            DaoException1 daoException = new DaoException1("dao.factory property is not defined in dao.properties file");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        impl = implProp;
    }

    public static DaoFactory1 getDAOFactory() {
        if (impl == null) {
            DaoException1 daoException = new DaoException1("DaoFactory1 was not initilized");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        if (impl.equals("jdbc")) {
            return new JdbcDaoFactory1();
        }
        DaoException1 daoException = new DaoException1("no Factories found for impl provided");
        logger.error(daoException.getMessage(), daoException);
        throw daoException;
    }

    public DaoFactory1() {
    }

    public abstract CustomerDao1 getCustomerDAO();

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}