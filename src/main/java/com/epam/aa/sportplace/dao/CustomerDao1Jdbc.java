package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CustomerDao1Jdbc implements CustomerDao1 {
    Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(JdbcDaoFactory1.class);

    public CustomerDao1Jdbc() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean insert(Customer customer) {
        //TODO: insert checks

        boolean success = false;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection.setAutoCommit(false);
            String stm = "INSERT INTO customer(first_name, last_name, birth_date) " +
                    "VALUES(?, ?, ?)";
            pst = connection.prepareStatement(stm);
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
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                //TODO: rethrow
                logger.error(e.getMessage(), e);
            }
        }
        return success;
    }
}