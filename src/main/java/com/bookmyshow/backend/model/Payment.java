package com.bookmyshow.backend.model;


public class Payment {
  private String bookingId;
  private String amount;
  private String method;
  private String status;

  public String getBookingId() {
    return bookingId;
  }

  public void setBookingId(String bookingId) {
    this.bookingId = bookingId;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
