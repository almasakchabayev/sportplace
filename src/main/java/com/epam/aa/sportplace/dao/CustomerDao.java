package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;

public interface CustomerDao extends Dao {
    boolean create(Customer customer);
    Customer read(Integer id);
}
