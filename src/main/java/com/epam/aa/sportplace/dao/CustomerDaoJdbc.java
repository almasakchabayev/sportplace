package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CustomerDaoJdbc implements CustomerDao {
    Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(CustomerDaoJdbc.class);

    public CustomerDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    //TODO: need to catch errors eventually, cannot always rethrow them
    public void create(Customer customer) {
        //TODO: create checks
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String stm = "INSERT INTO customer(first_name, last_name, birth_date) " +
                    "VALUES(?, ?, ?) RETURNING id;";
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

            rs = pst.executeQuery();
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
    }

    public Customer find(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String stm = "SELECT * FROM customer WHERE id=?;";
            pst = connection.prepareStatement(stm);
            pst.setInt(1, id);
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

    public void update(Customer transientObject) {
    }

    public void delete(Customer transientObject) {
    }
}