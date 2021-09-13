package com.bookmyshow.backend.service;

import com.bookmyshow.backend.config.Constants;
import com.bookmyshow.backend.jdbc.BookingDao;
import com.bookmyshow.backend.utility.BackendUtil;


import java.util.concurrent.Callable;


public class BookingService implements Callable<Boolean> {
    private String bookingId;


    BookingDao bookingDao;

    public BookingService(String bookingId) {
        this.bookingId = bookingId;
        this.bookingDao = new BookingDao(BackendUtil.getJdbcTemplate());
    }

    @Override
    public Boolean call() throws Exception {
        boolean result = false;
        while(true && !Thread.currentThread().isInterrupted()) {
            String status = bookingDao.findBookingStatus(bookingId);
            System.out.println(status);
            if(Constants.BookingStatus.CONFIRMED.equalsIgnoreCase(status)) {
                result = true;
                break;
            } else if(Constants.BookingStatus.CANCELLED.equalsIgnoreCase(status)) {
                result = false;
                break;
            }
            Thread.sleep(3000);
        }
        return result;
    }
}
