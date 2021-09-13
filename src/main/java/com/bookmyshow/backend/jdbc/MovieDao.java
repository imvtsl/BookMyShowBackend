package com.bookmyshow.backend.jdbc;

import com.bookmyshow.backend.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> findAllMovies() {
        return jdbcTemplate.query("select * from movies", new BeanPropertyRowMapper<Movie>(Movie.class));
    }

    public Movie findByName(String name) {
        return jdbcTemplate.queryForObject("select * from movies where name = ?",
                new BeanPropertyRowMapper<Movie>(Movie.class),
                name);
    }

    public int insert(Movie movie) {
        return jdbcTemplate.update(
                "insert into movies(name, duration, director, actors, summary, lang, genre, certificate) values(?, ?, ?, ?, ?, ?, ?, ?)",
                movie.getName(), movie.getDuration(), movie.getDirector(), movie.getActors(),
                movie.getSummary(), movie.getLang().toLowerCase(), movie.getGenre().toLowerCase(), movie.getCertificate().toUpperCase());
    }
}
