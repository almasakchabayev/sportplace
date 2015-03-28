package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
    private static final Logger logger = LoggerFactory.getLogger(DAOFactoryJdbc.class);

    //TODO: check that it is fine implementation: i.e. all the tuning from AppListener is done here and DAOFactoryJdbc
    // only overrides getCustomerDao method?
    private static DataSource ds;
    private static String impl;
    private Connection connection;

    public static void init(String implProp, DataSource dataSource) {
        //TODO: do I need throw of exception for impl? i tackle it in AppListener class as well
        if (implProp == null || implProp.isEmpty()) {
            DAOException daoException = new DAOException("dao.factory property is not defined in dao.properties file");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        if (dataSource == null) {
            DAOException daoException = new DAOException("no dataSource was provided");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        impl = implProp;
        ds = dataSource;
    }

    public static DAOFactory getFactory() {
        if (impl == null || ds == null) {
            DAOException daoException = new DAOException("DAOFactory was not initilized");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        if (impl.equals("jdbc")) {
            DAOFactory daoFactory = new DAOFactoryJdbc();
            try {
                daoFactory.setConnection(ds.getConnection());
            } catch (SQLException e) {
                logger.error("Could not fetch connection", e);
                throw new DAOException(e);
            }
            return daoFactory;
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