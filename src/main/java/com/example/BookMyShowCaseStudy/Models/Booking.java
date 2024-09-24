package com.example.BookMyShowCaseStudy.Models;

import java.util.Date;
import java.util.List;

public class Booking extends BaseModel {
    private BookingStatus bookingStatus;

    private List<ShowSeat> showSeats;

    private User user;
    private Date bookedAt;

    private Show show;
    private int amount;

    private List<Payment> payments;
}
