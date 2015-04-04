package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {
    private static final Logger logger = LoggerFactory.getLogger(JdbcDaoFactory.class);

    private static DataSource dataSource;
    private Connection connection;

    public JdbcDaoFactory() {
        if (dataSource == null) {
            DaoException daoException = new DaoException("dataSource is not defined, " +
                    "need to ne initialized first in StartAppListener");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
    }

    public static void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            DaoException daoException = new DaoException("DataSource failed to initialize in StartAppListener");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        JdbcDaoFactory.dataSource = dataSource;
        logger.info("Jdbc datasource initialised successfully");
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    private Connection getConnection() {
        if (connection != null) return connection;
        try {
            connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            DaoException daoException = new DaoException(
                    "Could not get connection from dataSource " + dataSource, e);
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
    }

    //execute daoCommand with closing connection
    @Override
    public <T> T execute(DaoCommand<T> daoCommand) {
        try {
            T result = daoCommand.execute(this);
            return result;
        } finally {
            try {
                getConnection().close();
                logger.debug("Connection closed, executed properly");
            } catch (SQLException e) {
                DaoException daoException = new DaoException(
                        "Connection did not close properly when executing daoCommand", e);
                logger.error(daoException.getMessage(), daoException);
                throw daoException;
            }
        }
    }

    // execute transaction/transactions with closing the connection
    @Override
    public <T> T transaction(DaoCommand<T> daoCommand) {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            DaoException daoException = new DaoException(
                    "Could not set autocommit to false for connection " + connection, e);
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        try {
            T result = daoCommand.execute(this);
            getConnection().commit();
            return result;
        }  catch (SQLException e) {
            DaoException daoException = new DaoException(
                    "Could not commit daoCommand", e);
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        } finally {
            try {
                getConnection().setAutoCommit(true);
                logger.debug("transaction executed properly");
            } catch (SQLException e) {
                DaoException daoException = new DaoException(
                        "Autocommit could not set to true when executing daoCommand", e);
                logger.error(daoException.getMessage(), daoException);
                throw daoException;
            }
        }
    }

    @Override
    public CustomerDao getCustomerDao() {
        return new CustomerDaoJdbc(getConnection());
    }
}