package com.example.BookMyShowCaseStudy.Repositories;

import com.example.BookMyShowCaseStudy.Models.Show;
import com.example.BookMyShowCaseStudy.Models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByShow(Show show);
}
