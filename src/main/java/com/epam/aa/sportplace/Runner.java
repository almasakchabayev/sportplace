package com.epam.aa.sportplace;

import com.epam.aa.sportplace.model.Customer;
import com.epam.aa.sportplace.validation.Validators;

public class Runner {
    public static void main(String[] args) throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Almas");
        customer.setLastName("Akchabayev");
        customer.setEmail("almas.akchabayev@gmail.com");
        customer.addPhoneNumber("87017511143");

        Validators.validate(customer);
    }
}
