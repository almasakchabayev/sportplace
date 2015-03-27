package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class JdbcDAOFactory extends DAOFactory {

    private static final Logger logger = LoggerFactory.getLogger(JdbcDAOFactory.class);

    @Override
    public CustomerDAO getCustomerDAO() {
        CustomerDAOJdbcImpl customerDAOJdbc = null;
        try {
            customerDAOJdbc = new CustomerDAOJdbcImpl();
            customerDAOJdbc.setCon(getDs().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDAOJdbc;
    }
}