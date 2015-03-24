package com.epam.aa.sportplace.model;

import java.util.ArrayList;
import java.util.List;

public class ContactInfo extends BaseEntity {
    private List<String> phoneNumbers;
    private String email;
    private Address address;

    //TODO: add validation
    public void setEmail(String email) {
        this.email = email;
    }

    //TODO: add validation
    public void addPhoneNumber(String phoneNumber) {
        if (phoneNumbers == null) phoneNumbers = new ArrayList<String>();
        phoneNumbers.add(phoneNumber);
    }
}
