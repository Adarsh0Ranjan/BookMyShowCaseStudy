package com.example.BookMyShowCaseStudy.Models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private String refNo;
    private int amount;
}
