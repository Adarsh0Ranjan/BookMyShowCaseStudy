package com.example.BookMyShowCaseStudy.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name;

    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection // because if list
    private List<Feature> features;
}
