package com.bookmyshow.backend.controller;

import com.bookmyshow.backend.config.Constants;
import com.bookmyshow.backend.model.Booking;
import com.bookmyshow.backend.model.Seat;
import com.bookmyshow.backend.jdbc.BookingDao;
import com.bookmyshow.backend.jdbc.MovieCinemaDao;
import com.bookmyshow.backend.jdbc.ShowDao;
import com.bookmyshow.backend.jdbc.VenueDao;
import com.bookmyshow.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    @Autowired
    VenueDao venueDao;

    @Autowired
    MovieCinemaDao movieCinemaDao;

    @Autowired
    ShowDao showDao;

    @Autowired
    BookingDao bookingDao;

    @PostMapping("/")
    public void bookTickets(@RequestBody Booking booking) {

        // given name, venue, time, seats
        // get venue id
        // get show id using venue  id and timestamp

        // get venue_seat_id using venue id and seat number
        // using venue_seat_id and show id, check seat status
        // if available mark them reserved
        // in loop check for payment
        // handle payment success/failure/timeout
        String venueId = venueDao.findVenueId(booking.getVenue(), booking.getLocation(), booking.getType());
        String showId = showDao.findShowId(venueId, booking.getTime());
        List<Seat> seatList = booking.getSeats();

        // get showseatIdList and cost
        List<String> showseatIdList = new ArrayList<>();
        int cost = 0;
        for(Seat seat : seatList) {

            Integer venueSeatId = venueDao.findVenueSeatId(venueId, seat.getSeatNumber(), seat.getType());
            String seatStaus = showDao.findSeatStatus(showId, venueSeatId);
            if(!Constants.SeatStatus.AVAILABLE.equalsIgnoreCase(seatStaus)) {

            } else {
                showDao.updateSeatStatus(showId, venueSeatId, Constants.SeatStatus.RESERVED);
                cost = cost + showDao.findCost(showId, venueSeatId);
                showseatIdList.add(showDao.findShowSeatId(showId, venueSeatId));
            }
        }

        // generate booking id and update booking and booking_seat table
        String bookingId =  UUID.randomUUID().toString();
        bookingDao.insertBooking(bookingId, Constants.BookingStatus.PENDING, Integer.toString(cost));
        for(String showSeatId : showseatIdList) {
            bookingDao.insertBookingSeat(bookingId, showSeatId);
        }

        // print cost and booking id for user
        System.out.println("bookingId:" + bookingId);
        System.out.println("cost:" + cost);


        // check for payments
        BookingService service = new BookingService(bookingId);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Boolean> paymentStatus = executorService.submit(service);
        boolean isPaymentSuccessful = false;
        try {
            isPaymentSuccessful = paymentStatus.get(180, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }

        paymentStatus.cancel(true);

        if(isPaymentSuccessful) {
            bookingDao.updateBookingStatus(bookingId, Constants.BookingStatus.CONFIRMED);
        } else {
            // release seats
            for(Seat seat : seatList) {
                Integer venueSeatId = venueDao.findVenueSeatId(venueId, seat.getSeatNumber(), seat.getType());
                showDao.updateSeatStatus(showId, venueSeatId, Constants.SeatStatus.AVAILABLE);

            }
            bookingDao.updateBookingStatus(bookingId, Constants.BookingStatus.CANCELLED);
        }


        return;
    }
}
