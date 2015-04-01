package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CustomerDaoJdbcOld implements CustomerDao {
    Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(CustomerDaoJdbcOld.class);

    public CustomerDaoJdbcOld(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Customer customer) {
        //TODO: create checks

        boolean success = false;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
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
            logger.info("new Customer inserted {}", customer);
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
            } catch (SQLException e) {
                //TODO: rethrow
                logger.error(e.getMessage(), e);
            }
        }
        return success;
    }

    public Customer read(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String stm = "SELECT * FROM customer WHERE id=1;";
            pst = connection.prepareStatement(stm);
            rs = pst.executeQuery();
            Customer result = new Customer();
            while (rs.next()) {
                result.setFirstName(rs.getString("first_name"));
            }
            return result;
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
            } catch (SQLException e) {
                //TODO: rethrow
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }
}