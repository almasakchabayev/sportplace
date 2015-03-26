package com.epam.aa.sportplace.dao;

public class JdbcDAOFactory implements DAOFactory {
    @Override
    public CustomerDAO getCustomerDAO(String type) {
        if (type.equalsIgnoreCase("postgresql")) {
            return new CustomerDAOJdbcImpl();
        } else {
            return new CustomerDAOJdbcImpl();
        }
    }
}