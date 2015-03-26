package com.epam.aa.sportplace.model;

import java.time.ZonedDateTime;

public class Booking extends BaseEntity {
    private SportPlace sportPlace;
    private Customer customer;
//    private Payment payment;
    private ZonedDateTime bookedFrom;
    private ZonedDateTime bookedTo;
}
