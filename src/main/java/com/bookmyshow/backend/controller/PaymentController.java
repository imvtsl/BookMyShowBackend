package com.bookmyshow.backend.controller;

import com.bookmyshow.backend.config.Constants;
import com.bookmyshow.backend.model.Payment;
import com.bookmyshow.backend.jdbc.BookingDao;
import com.bookmyshow.backend.jdbc.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    @Autowired
    BookingDao bookingDao;

    @Autowired
    PaymentDao paymentDao;

    @PostMapping("/")
    public void acceptPayment(@RequestBody Payment payment) {
        // validate payment pojo
        String status = payment.getStatus();

        if(Constants.PaymentStatus.SUCCESS.equalsIgnoreCase(status)) {
            String amountOwed = bookingDao.findAmount(payment.getBookingId());
            if(amountOwed.equalsIgnoreCase(payment.getAmount())) {
                paymentDao.insertPayment(payment.getBookingId(), payment.getMethod(), Constants.PaymentStatus.SUCCESS);
                bookingDao.updateBookingStatus(payment.getBookingId(), Constants.BookingStatus.CONFIRMED);
            } else {
                handleFailedPayment(payment);
            }
        } else if(Constants.PaymentStatus.FAILED.equalsIgnoreCase(status)) {
            handleFailedPayment(payment);
        }
    }

    private void handleFailedPayment(Payment payment) {
        paymentDao.insertPayment(payment.getBookingId(), payment.getMethod(), Constants.PaymentStatus.FAILED);
        bookingDao.updateBookingStatus(payment.getBookingId(), Constants.BookingStatus.CANCELLED);
    }
}
