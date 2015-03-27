package com.epam.aa.sportplace.dao;

import javax.sql.DataSource;

public abstract class DAOFactory {
    private static DAOFactory daoFactory;
    private static DataSource ds;

    public static DAOFactory init(String impl, DataSource dataSource) {
        if (impl.equals("jdbc")) {
            if (daoFactory == null) daoFactory = new JdbcDAOFactory();
            //TODO:exception throw in else?
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