package com.bookmyshow.backend.model;


public class ShowSeat {

  private String id;
  private String showId;
  private String price;
  private String status;
  private Integer venueSeatId;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getShowId() {
    return showId;
  }

  public void setShowId(String showId) {
    this.showId = showId;
  }


  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public Integer getVenueSeatId() {
    return venueSeatId;
  }

  public void setVenueSeatId(Integer venueSeatId) {
    this.venueSeatId = venueSeatId;
  }

}
