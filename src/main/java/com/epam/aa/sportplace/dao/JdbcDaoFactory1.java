package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class JdbcDaoFactory1 extends DaoFactory1 {

    private static final Logger logger = LoggerFactory.getLogger(JdbcDaoFactory1.class);

    private static DataSource dataSource;

    public JdbcDaoFactory1() {
        if (dataSource == null) {
            DaoException1 daoException = new DaoException1("dataSourxe is not defined, need to ne initialized first");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        try {
            setConnection(dataSource.getConnection());
        } catch (SQLException e) {
            DaoException1 daoException = new DaoException1("Could not get connection from dataSource");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        JdbcDaoFactory1.dataSource = dataSource;
    }

    public CustomerDao1 getCustomerDAO() {
        CustomerDao1Jdbc customerDAOJdbc = new CustomerDao1Jdbc();
        //TODO: should I set connection, or take it from the Factory in DAOImpl?
        customerDAOJdbc.setConnection(getConnection());
        return customerDAOJdbc;
    }
}