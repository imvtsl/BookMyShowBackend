package com.bookmyshow.backend.jdbc;

import com.bookmyshow.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieCinemaDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<MovieCinema> findCinemasByMovie(String movieName) {
        return jdbcTemplate.query("select v.name, v.location, ms.start_timestamp from venue v join movie_show ms on (v.id = ms.venue_id) join movies m on(ms.movie_id = m.id) where m.name = ?",
                new BeanPropertyRowMapper<MovieCinema>(MovieCinema.class),
                movieName);
    }

    public List<MovieCinemaSeat> findSeatsByMovieAndCinema(String movieName, String cinemaName) {
        return jdbcTemplate.query("select ms.start_timestamp, vs.seat_number,  vs.type, ss.price, ss.status from venue v join movie_show ms on (v.id = ms.venue_id) join movies m on(ms.movie_id = m.id) join show_seat ss on (ss.show_id = ms.show_id) join venue_seat vs on (vs.id = ss.venue_seat_id) where m.name = ? and v.name = ?",
                new BeanPropertyRowMapper<MovieCinemaSeat>(MovieCinemaSeat.class),
                movieName, cinemaName);
    }

    public List<CinemaMovie> findMoviesByCinemaName(String cinemaName) {
        return jdbcTemplate.query("select m.name, m.duration, m.director, m.actors, m.summary, m.lang, m.genre, m.certificate, ms.start_timestamp from movies m join movie_show ms on (m.id = ms.movie_id) join venue v on (ms.venue_id = v.id) where v.name = ?",
            new BeanPropertyRowMapper<CinemaMovie>(CinemaMovie.class),
            cinemaName);

    }

    public int insertShowMovie(String showId, String movieId) {
        return jdbcTemplate.update(
                "insert into show_movie(show_id, movie_id) values(?, ?)",
                showId, movieId);
    }

}
