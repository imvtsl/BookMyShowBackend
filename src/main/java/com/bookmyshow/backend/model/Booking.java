package com.bookmyshow.backend.model;

import java.util.List;

public class Booking {

  private String name;
  private String venue;
  private String location;
  private String type;
  private String time;
  private List<Seat> seats;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }
}
