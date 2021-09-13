package com.bookmyshow.backend.jdbc;

import com.bookmyshow.backend.utility.BackendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public BookingDao() {

    }

    public BookingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String findBookingStatus(String bookingId) {
        return jdbcTemplate.queryForObject("select status from booking where id = ?",
                String.class,
                bookingId);
    }

    public String findAmount(String bookingId) {
        return jdbcTemplate.queryForObject("select amount from booking where id = ?",
                String.class,
                bookingId);
    }

    public int insertBooking(String bookingId, String bookingStatus, String amount) {
        return jdbcTemplate.update("insert into booking values(?, ?, ?, ?)",
                bookingId, BackendUtil.getCurrentTimeStamp(), bookingStatus, amount);
    }

    public int updateBookingStatus(String bookingId, String bookingStatus) {
        return jdbcTemplate.update("update booking set status = ? where id = ?",
                bookingStatus, bookingId);
    }

    public int insertBookingSeat(String bookingId, String showSeatId) {
        return jdbcTemplate.update("insert into booking_seat(booking_id, show_seat_id) values(?, ?)",
                bookingId, showSeatId);
    }

}
