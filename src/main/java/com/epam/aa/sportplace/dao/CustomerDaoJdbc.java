package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CustomerDaoJdbc implements GenericDao<Customer, Integer> {
    Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(CustomerDaoJdbc.class);

    public CustomerDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    public Integer create(Customer customer) {
        //TODO: create checks
        PreparedStatement pst = null;
        ResultSet rs = null;
        Integer objectId = null;

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
            while (rs.next()) objectId = rs.getInt(1);
            logger.info("new Customer inserted {} with assigned id {}", customer, objectId);
            return objectId;
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
        //TODO: delete when added rethrows
        return null;
    }

    public Customer read(Integer id) {
        return null;
    }

    public boolean update(Customer transientObject) {
        return false;
    }

    public boolean delete(Customer transientObject) {
        return false;
    }
}