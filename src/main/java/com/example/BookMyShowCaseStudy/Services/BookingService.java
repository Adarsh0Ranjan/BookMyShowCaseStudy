package com.example.BookMyShowCaseStudy.Services;
import com.example.BookMyShowCaseStudy.Models.*;
import com.example.BookMyShowCaseStudy.Repositories.BookingRepository;
import com.example.BookMyShowCaseStudy.Repositories.ShowRepository;
import com.example.BookMyShowCaseStudy.Repositories.ShowSeatRepository;
import com.example.BookMyShowCaseStudy.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;
    private PriceCalculatorService priceCalculatorService;

    public BookingService(
            BookingRepository bookingRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository,
            UserRepository userRepository,
            PriceCalculatorService priceCalculatorService
    ) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculatorService = priceCalculatorService;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE) // we are using this to start transaction over whole function we are doing this today we wnt doing actual part beacuse we will have actual db there
    public Booking bookMovie(Long userId, List<Long> showSeatIds, Long showId) {
        /*-----Start trx here for today-------
         * 1. Get user from the DB using userID
         * 2. Get show from the DB using showID
         * -------Transaction----------
         * 3. Get showseat from DB using showseatIDs
         * 4. Check if they are available
         * 5. If not, throw error
         * 6. If yes, update status to locked
         * 7. save to the DB
         * -------End Transaction----------
         * 8. Create the booking object and set the attributes
         * 9. Return to controller
         * ------end trx here today-------
         * */

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new RuntimeException(); // TODO: Create Runtime Exception
        }

        User bookedBy = userOptional.get();

        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()) {
            throw new RuntimeException(); // TODO: Create ShowNotFoundException
        }
        Show bookedShow = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat: showSeats) {
            if (!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes() > 15
                    ))) {
                throw new RuntimeException();
            }
        }

        List<ShowSeat> bookedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            bookedShowSeats.add(showSeatRepository.save(showSeat));
        }
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(bookedShowSeats);
        booking.setUser(bookedBy);
        booking.setBookedAt(new Date());
        booking.setShow(bookedShow);
        booking.setTotalAmount(priceCalculatorService
                .calculatePrice(bookedShow, showSeats));

        booking.setPayments(new ArrayList<>());

        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }
}
