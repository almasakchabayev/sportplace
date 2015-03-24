package com.epam.aa.sportplace.dao;

public class CustomerDAOFactory {
    public static CustomerDAO getUserDAO(String type) {
        if (type.equalsIgnoreCase("postgresql")) {
            return new CustomerDAOPostgreSQLImpl();
        } else {
            return new CustomerDAOPostgreSQLImpl();
        }
    }
}
