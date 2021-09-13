package com.bookmyshow.backend.controller;

import com.bookmyshow.backend.model.Movie;
import com.bookmyshow.backend.model.MovieCinema;
import com.bookmyshow.backend.model.MovieCinemaSeat;
import com.bookmyshow.backend.jdbc.MovieCinemaDao;
import com.bookmyshow.backend.jdbc.MovieDao;
import com.bookmyshow.backend.utility.BackendUtil;
import com.bookmyshow.backend.validation.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    @Autowired
    MovieDao movieDao;

    @Autowired
    MovieCinemaDao movieCinemaDao;

    @GetMapping(value = "/")
    public List<Movie> getAllMovies() {
        System.out.println("All movies");
        List<Movie> movies = movieDao.findAllMovies();
        return movies;
    }

    @GetMapping(value = "/{movieName}")
    public Movie getMovieByName(@PathVariable String movieName) {
        System.out.println(movieName);
        try {
            return movieDao.findByName(movieName);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/{movieName}/cinemas")
    public List<MovieCinema> getCinemasByMovie(@PathVariable String movieName) {
        System.out.println(movieName);
        List<MovieCinema> movieCinemaList = movieCinemaDao.findCinemasByMovie(movieName);
        return movieCinemaList;
    }

    @GetMapping(value = "/{movieName}/cinemas/{cinemaName}")
    public List<MovieCinemaSeat> getTimeByCinemaNameMovieName(@PathVariable String movieName, @PathVariable String cinemaName) {
        System.out.println(movieName + " " + cinemaName);
        List<MovieCinemaSeat> timeList = movieCinemaDao.findSeatsByMovieAndCinema(movieName, cinemaName);
        return timeList;
    }

    @PostMapping(value = "/")
    public void addMovie(@RequestBody Movie movie) {
        List<String> errorLists = MovieValidator.validateMovie(movie);
        if(errorLists.isEmpty()) {
            movieDao.insert(movie);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BackendUtil.convertToString(errorLists));
        }

    }

}
