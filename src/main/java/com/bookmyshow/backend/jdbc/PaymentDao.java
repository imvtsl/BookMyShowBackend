package com.bookmyshow.backend.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insertPayment(String bookingId, String method, String status) {
        return jdbcTemplate.update("insert into payment(booking_id, method, status) values(?, ?, ?)",
                bookingId, method, status);
    }
}
