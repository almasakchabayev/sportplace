package com.epam.aa.sportplace.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Customer extends User {
    private Company company;
    private List<SportPlace> favourited;
    private List<SportPlace> booked;
    //TODO: split to Company and Person
}
