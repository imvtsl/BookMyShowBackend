package com.bookmyshow.backend.controller;

import com.bookmyshow.backend.config.Constants;
import com.bookmyshow.backend.model.*;
import com.bookmyshow.backend.jdbc.ShowDao;
import com.bookmyshow.backend.jdbc.VenueDao;
import com.bookmyshow.backend.jdbc.MovieCinemaDao;
import com.bookmyshow.backend.jdbc.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.bookmyshow.backend.utility.BackendUtil.buildShowSeat;

@RestController
@RequestMapping("/v1/cinemas")
public class CinemaController {

    @Autowired
    VenueDao venueDao;

    @Autowired
    MovieCinemaDao movieCinemaDao;

    @Autowired
    MovieDao movieDao;

    @Autowired
    ShowDao showDao;

    @GetMapping(value = "/")
    public List<Venue> getAllCinemas() {
        System.out.println("All cinemas");
        List<Venue> venueList = venueDao.findAllVenuesByType(Constants.VenueType.CINEMA);
        return venueList;
    }

    @PostMapping(value = "/")
    public void insertCinema(@RequestBody VenueLayout venueLayout) {
        venueDao.insertVenue(venueLayout.getName(), venueLayout.getLocation(), Constants.VenueType.CINEMA);
        // insert venue
        // insert seats
        String venueId = venueDao.findVenueId(venueLayout.getName(), venueLayout.getLocation(), Constants.VenueType.CINEMA);
        List<Seat> seatList = venueLayout.getSeats();
        for(Seat seat : seatList) {
            venueDao.insertVenueLayout(venueId, seat);
        }

    }


    @GetMapping(value = "/{cinemaName}")
    public Venue getCinemaByName(@PathVariable String cinemaName) {
        System.out.println(cinemaName);
        try {
            return venueDao.findVenueByTypeByName(cinemaName, Constants.VenueType.CINEMA);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping(value = "/{cinemaName}/movies")
    public List<CinemaMovie> getMoviesByCinema(@PathVariable String cinemaName) {
        System.out.println(cinemaName);
        // validate id
        // return movies for a cinmea, return 404 if id not found
        List<CinemaMovie> moviesList = movieCinemaDao.findMoviesByCinemaName(cinemaName);

        return moviesList;
    }

    @PostMapping(value = "/{cinemaName}/movies")
    public void addMovieToCinema(@RequestBody MovieShow movieShow, @PathVariable String cinemaName) {
        Venue venue;
        Movie movie;
        try {
            venue = venueDao.findVenueByTypeByName(cinemaName, Constants.VenueType.CINEMA);
            // check if movie exists
            String movieName = movieShow.getName();
            movie = movieDao.findByName(movieName);
            showDao.insertShow(venue.getId(), movieShow.getStart_timestamp());
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        String showId = showDao.findShowId(venue.getId(), movieShow.getStart_timestamp());
        movieCinemaDao.insertShowMovie(showId, movie.getId());
        List<Seat> seatList = movieShow.getSeats();
        for(Seat seat : seatList) {
            System.out.println("seat details");
            System.out.println(venue.getId());
            System.out.println(seat.getSeatNumber());
            System.out.println(seat.getType());
            Integer venueSeatId;
            try {
                venueSeatId = venueDao.findVenueSeatId(venue.getId(), seat.getSeatNumber(), seat.getType());
            } catch (DataAccessException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
            ShowSeat showSeat = buildShowSeat(seat, showId, Constants.SeatStatus.AVAILABLE, venueSeatId);
            showDao.insertShowSeat(showSeat);
        }
    }

    @PutMapping(value = "/{cinemaName}")
    public void updateCinema(@PathVariable String cinemaName, @RequestBody VenueLayout venueLayout) {
        // delete cinema ...cascade delete
        // insert cinema in venue
        // insert venue seat
        System.out.println(cinemaName);
        venueDao.deleteByName(cinemaName);
        this.insertCinema(venueLayout);
    }


}
