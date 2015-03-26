package com.epam.aa.sportplace.dao;

import java.util.ResourceBundle;

public interface DAOFactory {
    public static DAOFactory getFactory() {
        ResourceBundle rb = ResourceBundle.getBundle("dao");
        String impl = rb.getString("dao.factory");
        switch (impl) {
            case "jdbc" : return new JdbcDAOFactory();
            default : return null;
        }
    }
    public CustomerDAO getCustomerDAO();
}