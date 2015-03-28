package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class JdbcDAOFactory extends DAOFactory {

    private static final Logger logger = LoggerFactory.getLogger(JdbcDAOFactory.class);

    private static DataSource dataSource;

    public JdbcDAOFactory() {
        if (dataSource == null) {
            DAOException daoException = new DAOException("dataSourxe is not defined, need to ne initialized first");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        try {
            setConnection(dataSource.getConnection());
        } catch (SQLException e) {
            DAOException daoException = new DAOException("Could not get connection from dataSource");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        JdbcDAOFactory.dataSource = dataSource;
    }

    public CustomerDAO getCustomerDAO() {
        CustomerDAOJdbc customerDAOJdbc = new CustomerDAOJdbc();
        //TODO: should I set connection, or take it from the Factory in DAOImpl?
        customerDAOJdbc.setConnection(getConnection());
        return customerDAOJdbc;
    }
}