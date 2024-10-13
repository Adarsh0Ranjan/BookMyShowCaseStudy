package com.example.BookMyShowCaseStudy.Services;

import com.example.BookMyShowCaseStudy.Models.Show;
import com.example.BookMyShowCaseStudy.Models.ShowSeat;
import com.example.BookMyShowCaseStudy.Models.ShowSeatType;
import com.example.BookMyShowCaseStudy.Repositories.ShowSeatTypeRepository;

import java.util.List;

public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(Show show, List<ShowSeat> showSeats) {
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository
                .findAllByShow(show);

        int amount = 0;
        for(ShowSeat showSeat: showSeats) { // ShowSeats selected by the user
            for(ShowSeatType showSeatType: showSeatTypes) { // All show seat types that exist in the show
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                }
            }
        }

        return amount;
    }


}
