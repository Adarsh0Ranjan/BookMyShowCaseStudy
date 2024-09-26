package com.example.BookMyShowCaseStudy.Models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class BaseModel {
    @Id
    private Long id;
    private Date cratedAt;
    private Date updatedAt;
}
