package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private static final Logger logger = LoggerFactory.getLogger(JdbcDaoFactory.class);

    private static DataSource dataSource;

    public JdbcDaoFactory() {
        if (dataSource == null) {
            DaoException daoException = new DaoException("dataSourxe is not defined, need to ne initialized first");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        try {
            setConnection(dataSource.getConnection());
        } catch (SQLException e) {
            DaoException daoException = new DaoException("Could not get connection from dataSource");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        JdbcDaoFactory.dataSource = dataSource;
    }

    public CustomerDao getCustomerDAO() {
        CustomerDaoJdbc customerDAOJdbc = new CustomerDaoJdbc();
        //TODO: should I set connection, or take it from the Factory in DAOImpl?
        customerDAOJdbc.setConnection(getConnection());
        return customerDAOJdbc;
    }
}