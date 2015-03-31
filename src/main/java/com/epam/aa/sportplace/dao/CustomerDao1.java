package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;

public interface CustomerDao1 extends Dao1 {
    public boolean insert(Customer customer);
}
