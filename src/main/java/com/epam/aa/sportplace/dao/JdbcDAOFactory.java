package com.epam.aa.sportplace.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcDAOFactory implements DAOFactory {
    private static DataSource ds;
    private static Logger logger = Logger.getLogger(JdbcDAOFactory.class.getName());

    static {
        try {
            InitialContext cxt = new InitialContext();
            Context envContext = (Context) cxt.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/SportplaceDB");

            //throws ClassNotFoundException
            Class.forName("org.postgresql.Driver");
        } catch (NamingException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    @Override
    public CustomerDAO getCustomerDAO() {
        CustomerDAOJdbcImpl customerDAOJdbc = null;
        try {
            customerDAOJdbc = new CustomerDAOJdbcImpl();
            customerDAOJdbc.setCon(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDAOJdbc;
    }
}