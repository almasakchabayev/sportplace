package com.epam.aa.sportplace.dao;

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
        //TODO: do I need to check if connection has been closed before and gets a new connection from datasource or
        // one instance of datafactory per transaction is fine?
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

    private Connection getTxConnection() {
        try {
            getConnection().setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            DaoException daoException = new DaoException(
                    "Could not set autocommit to false for connection " + connection, e);
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
    }

    @Override
    public Object executeTx(DaoCommand daoCommand) {
        try {
            Object result = daoCommand.execute(this);
            getConnection().commit();
            return result;
        } catch (SQLException e) {
            DaoException daoException = new DaoException(
                    "Could not commit daoCommand", e);
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        } finally {
            try {
                getConnection().setAutoCommit(true);
                logger.info("autocommit true");
                getConnection().close();
            } catch (SQLException e) {
                DaoException daoException = new DaoException(
                        "Connection did not close properly when executing daoCommand", e);
                logger.error(daoException.getMessage(), daoException);
                throw daoException;
            }
            //TODO can I put information in here about daoCommand?
//            try {
//                daoCommand.getClass().getMethod("execute", null);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
            logger.info("transaction successfully executed");
        }
    }

    @Override
    public GenericDao getCustomerDao() {
        return new CustomerDaoJdbc(getTxConnection());
    }
}