package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

//TODO: DAOException
public class CustomerDAOJdbcImpl implements CustomerDAO {
    Connection con;
    //TODO: how to use Logger
    private static final Logger logger = LoggerFactory.getLogger(JdbcDAOFactory.class);
    public CustomerDAOJdbcImpl() {
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    @Override
    public boolean insert(Customer customer) {

        boolean success = false;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String stm = "INSERT INTO customers(first_name, last_name, birth_date) " +
                    "VALUES(?, ?, ?)";
            pst = con.prepareStatement(stm);
            pst.setString(1, customer.getFirstName());
            pst.setString(2, customer.getLastName());
            //TODO: relationship to contactinfo
//            if (customer.getContactInfo() != null) {
//                ContactInfoDAO contactInfoDAO = new ContactInfoDAO();
//                contactInfoDAO.insertWithReturningId(customer.getContactInfo());
//            }
            //TODO: what to do about password?
            Date birthDateToStore = Util.getSQLDateFromLocalDate(customer.getBirthDate());
            pst.setDate(3, birthDateToStore);

            pst.executeUpdate();
            success = true;
        } catch (SQLException e) {
            //TODO: rethrow
            logger.error(e.getMessage(), e);
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

            } catch (SQLException e) {
                //TODO: rethrow
                logger.error(e.getMessage(), e);
            }
        }
        return success;
    }
}