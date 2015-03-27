package com.epam.aa.sportplace.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class JdbcDAOFactory implements DAOFactory {
    private static DataSource ds;
    private static final Logger logger = LoggerFactory.getLogger(JdbcDAOFactory.class);

    static {
        try {
            InitialContext cxt = new InitialContext();
            Context envContext = (Context) cxt.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/SportplaceDB");
        } catch (NamingException e) {
            //TODO: rethrow
            logger.error(e.getMessage(), e);
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