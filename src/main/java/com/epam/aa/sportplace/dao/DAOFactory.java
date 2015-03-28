package com.epam.aa.sportplace.dao;

import javax.sql.DataSource;

public abstract class DAOFactory {
    //TODO: check that it is fine implementation: i.e. all the tuning from AppListener is done here and JdbcDAOFactory
    // only overrides getCustomerDao method?
    private static DAOFactory daoFactory;
    private static DataSource ds;

    public static DAOFactory init(String impl, DataSource dataSource) {
        //TODO: do I need throw of exception for impl? i tackle it in AppListener class as well
        if (impl.equals("jdbc")) {
            if (daoFactory == null) daoFactory = new JdbcDAOFactory();
            //TODO:exception throw in else? but datasourse in AppListener does not throw any exceptions
            if (dataSource != null) daoFactory.setDs(dataSource);
            return daoFactory;
        }
        return null;
    }
    public static DAOFactory getFactory() {
        return daoFactory;
    }

    public static void setDs(DataSource ds) {
        DAOFactory.ds = ds;
    }

    public static DataSource getDs() {
        return ds;
    }

    public abstract CustomerDAO getCustomerDAO();
}