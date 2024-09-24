package com.example.BookMyShowCaseStudy.Models;
import java.util.Date;

public class ShowSeat extends BaseModel {
    private Show show;

    private Seat seat;
    private ShowSeatStatus showSeatStatus;

    private Date blockedAt;
}
