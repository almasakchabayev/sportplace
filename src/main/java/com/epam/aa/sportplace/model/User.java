package com.epam.aa.sportplace.model;


import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;

public abstract class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private ContactInfo contactInfo; // email, address, contacts
    //TODO: what to do about password?
    private String password;
    //TODO: double check that for birthdays fine
    private LocalDate birthDate;

    @XmlElement(name="firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        if (contactInfo == null) contactInfo = new ContactInfo();
        contactInfo.setEmail(email);
    }


    public void addPhoneNumber(String phoneNumber) {
        if (contactInfo == null) contactInfo = new ContactInfo();
        contactInfo.addPhoneNumber(phoneNumber);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
