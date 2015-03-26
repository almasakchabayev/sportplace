package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.util.Util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO: DAOException
public class CustomerDAOJdbcImpl implements CustomerDAO {
    @Override
    public boolean insert(Customer customer) {
        boolean success = false;
        Connection con = null;
        PreparedStatement pst = null;
        DataSource ds;
        ResultSet rs = null;

        try {
            // throws NamingException
            InitialContext cxt = new InitialContext();
            Context envContext  = (Context)cxt.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/SportplaceDB");

            //throws ClassNotFoundException
            Class.forName("org.postgresql.Driver");

            //throws SQLException
            con = ds.getConnection();
            String stm = "INSERT INTO customers(first_name, last_name, birth_date) " +
                    "VALUES(?, ?, ?)";
            pst = con.prepareStatement(stm);
            pst.setString(1, customer.getFirstName());
            pst.setString(2, customer.getLastName());
//            if (customer.getContactInfo() != null) {
//                ContactInfoDAO contactInfoDAO = new ContactInfoDAO();
//                contactInfoDAO.insertWithReturningId(customer.getContactInfo());
//            }
//            //TODO: what to do about password?
            Date birthDateToStore = Util.getSQLDateFromLocalDate(customer.getBirthDate());
            pst.setDate(3, birthDateToStore);

            pst.executeUpdate();
            success = true;
        } catch (NamingException e) {
            Logger logger = Logger.getLogger(CustomerDAOJdbcImpl.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            Logger logger = Logger.getLogger(CustomerDAOJdbcImpl.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CustomerDAOJdbcImpl.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(CustomerDAOJdbcImpl.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return success;
    }
}