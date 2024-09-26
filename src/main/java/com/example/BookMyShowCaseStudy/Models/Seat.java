package com.example.BookMyShowCaseStudy.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String num;

    @OneToOne
    private SeatType seatType;

    private int rowVal;
    private int colVal;
}
