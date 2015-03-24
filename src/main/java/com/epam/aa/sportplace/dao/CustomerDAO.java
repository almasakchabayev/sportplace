package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;

public interface CustomerDAO extends DAO {
    public void insert(Customer customer);
}
