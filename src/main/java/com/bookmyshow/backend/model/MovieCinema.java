package com.bookmyshow.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieCinema {
    String name;
    String location;
    String start_timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(String start_timestamp) {
        this.start_timestamp = start_timestamp;
    }
}
