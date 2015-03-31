package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;

public interface CustomerDao extends Dao {
    public boolean insert(Customer customer);
}
