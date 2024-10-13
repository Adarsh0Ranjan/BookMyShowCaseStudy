package com.example.BookMyShowCaseStudy.Controllers;

import Dtos.BookMovieRequestDto;
import Dtos.BookMovieResponseDto;
import Dtos.ResponseStatus;
import com.example.BookMyShowCaseStudy.Models.Booking;
import com.example.BookMyShowCaseStudy.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto requestDto) {
        BookMovieResponseDto responseDto = new BookMovieResponseDto();
        Booking booking;
        try {
            booking = bookingService.bookMovie(
                    requestDto.getUserId(),
                    requestDto.getShowSeatsIds(),
                    requestDto.getShowId()
            );

            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setAmount(booking.getTotalAmount());
            responseDto.setBookingId(booking.getId());
        } catch (Exception ex) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
