package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOFactoryJdbc extends DAOFactory {

    private static final Logger logger = LoggerFactory.getLogger(DAOFactoryJdbc.class);

    public CustomerDAO getCustomerDAO() {
        CustomerDAOJdbc customerDAOJdbc = new CustomerDAOJdbc();
        //TODO: should I set connection, or take it from the Factory in DAOImpl?
        customerDAOJdbc.setCon(getConnection());
        return customerDAOJdbc;
    }
}