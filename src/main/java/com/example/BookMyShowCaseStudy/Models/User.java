package com.example.BookMyShowCaseStudy.Models;
import java.util.List;

public class User extends BaseModel {
    private String name;
    private String email;

    private List<Booking> bookings;
    private String password;
}
