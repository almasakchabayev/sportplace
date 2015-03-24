package com.epam.aa.sportplace.model;

import java.time.LocalDateTime;
import java.util.List;

public class SportPlace extends BaseEntity{
    private String size;
//    private List<TimeSlot> timetable;
    private boolean covered; // open
    private int maxNumberOfPeople;
    private boolean changingRoom;
    private boolean bathRoom;
    private boolean lighting;
    private String floorCoverage;
    private String otherInfrastructureFeatures;
    private String description;
    private LocalDateTime openFrom;
    private LocalDateTime closedFrom;
    private double pricePerHour;
    private Address address; // if a company has sport places in different locations
    private Company company;
    private List<Sport> sports;
    private List<Image> images;
    private List<Booking> bookings;
//    private List<PaymentType> paymentTypes;
//    private List<Rating> ratings; // Service, Quality of Coverage, etc.
}