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
            DaoException daoException = new DaoException("dataSourxe is not defined, " +
                    "need to ne initialized first in StartAppListener");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        try {
            connection = dataSource.getConnection();
            logger.info("JdbcDaoFactory created successfully");
        } catch (SQLException e) {
            DaoException daoException = new DaoException("Could not get connection from dataSource");
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

    @Override
    public Connection getConnection() throws DaoException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            DaoException daoException = new DaoException(e);
            logger.error("Could not get connection from dataSource {}", dataSource, daoException);
            throw daoException;
        }
    }

    @Override
    protected Connection getTxConnection() {
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object transaction(DaoCommand daoCommand) {
        Object result = daoCommand.execute(this);
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public CustomerDao getCustomerDao() {
        return new CustomerDaoJdbc(getConnection());
    }
}