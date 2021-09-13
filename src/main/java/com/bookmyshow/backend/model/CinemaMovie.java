package com.bookmyshow.backend.model;


public class CinemaMovie {

  private String id;
  private String name;
  private String duration;

  private String director;
  private String actors;
  private String summary;
  private String lang;
  private String genre;
  private String certificate;
  private String start_timestamp;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }


  public String getActors() {
    return actors;
  }

  public void setActors(String actors) {
    this.actors = actors;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }


  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }


  public String getCertificate() {
    return certificate;
  }

  public void setCertificate(String certificate) {
    this.certificate = certificate;
  }

  public String getStart_timestamp() {
    return start_timestamp;
  }

  public void setStart_timestamp(String start_timestamp) {
    this.start_timestamp = start_timestamp;
  }
}
