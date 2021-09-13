package com.bookmyshow.backend.jdbc;

import com.bookmyshow.backend.model.Seat;
import com.bookmyshow.backend.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VenueDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Venue> findAllVenuesByType(String type) {
        return jdbcTemplate.query("select * from venue where type = ?",
                new BeanPropertyRowMapper<Venue>(Venue.class),
                type);
    }

    public Venue findVenueByTypeByName(String name, String type) {
        return jdbcTemplate.queryForObject("select * from venue where type = ? and name = ?",
                new BeanPropertyRowMapper<Venue>(Venue.class),
                type, name);
    }

    public String findVenueId(String name, String location, String type) {
        return jdbcTemplate.queryForObject("select id from venue where name = ? and location = ? and type = ?",
                String.class,
                name, location, type);
    }

    public Integer findVenueSeatId(String venueId, String seatNumber, String type) {
        return jdbcTemplate.queryForObject("select id from venue_seat where venue_id = ? and seat_number = ? and type = ?",
                Integer.class,
                venueId, seatNumber, type);
    }


    public int deleteByName(String name) {
        return jdbcTemplate.update("delete from venue where name = ?",
                name);
    }

    public int insertVenue(String name, String location, String type) {
        return jdbcTemplate.update("insert into venue(name, location, type) values(?, ?, ?)",
                name, location, type.toLowerCase());
    }

    public int insertVenueLayout(String venueId, Seat seat) {
        return jdbcTemplate.update("insert into venue_seat(venue_id, seat_number, type) values(?, ?, ?)",
                venueId, seat.getSeatNumber(), seat.getType());
    }
}
