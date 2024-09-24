package com.example.BookMyShowCaseStudy.Models;
import java.util.Date;
import java.util.List;

public class Show extends BaseModel {

    private Movie movie;
    private Date startTime;
    private Date endTime;

    private Screen screen;

    private List<Feature> features;
}
