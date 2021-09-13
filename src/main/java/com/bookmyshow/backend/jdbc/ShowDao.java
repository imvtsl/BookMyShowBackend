package com.bookmyshow.backend.jdbc;

import com.bookmyshow.backend.model.ShowSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShowDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public String findShowId(String venueId, String startTimestamp) {
        return jdbcTemplate.queryForObject("select id from show where venue_id = ? and start_timestamp = to_timestamp(?, 'dd-mm-rr hh24:mi:ss')",
                String.class,
                venueId, startTimestamp);
    }

    public String findSeatStatus(String showId, Integer venueSeatId) {
        return jdbcTemplate.queryForObject("select status from show_seat where show_id = ? and venue_seat_id = ?",
                String.class,
                showId, venueSeatId);
    }

    public Integer findCost(String showId, Integer venueSeatId) {
        return jdbcTemplate.queryForObject("select price from show_seat where show_id = ? and venue_seat_id = ?",
                Integer.class,
                showId, venueSeatId);
    }

    public String findShowSeatId(String showId, Integer venueSeatId) {
        return jdbcTemplate.queryForObject("select id from show_seat where show_id = ? and venue_seat_id = ?",
                String.class,
                showId, venueSeatId);
    }

    public int insertShow(String venueId, String startTimestamp) {
        System.out.println(venueId);
        System.out.println(startTimestamp);
        return jdbcTemplate.update(
                "insert into show(venue_id, start_timestamp) values(?, to_timestamp(?, 'dd-mm-rr hh24:mi:ss'))",
                venueId, startTimestamp);
    }

    public int insertShowSeat(ShowSeat showSeat) {
        return jdbcTemplate.update("insert into show_seat(show_id, price, status, venue_seat_id) values(?, ?, ?, ?)",
                showSeat.getShowId(), showSeat.getPrice(), showSeat.getStatus(), showSeat.getVenueSeatId()
                );
    }

    public int updateSeatStatus(String showId, Integer venueSeatId, String status) {
        return jdbcTemplate.update("update show_seat set status = ? where show_id = ? and venue_seat_id = ?",
                status, showId, venueSeatId);
    }


}
