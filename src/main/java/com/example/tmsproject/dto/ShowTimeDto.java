package com.example.tmsproject.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class ShowTimeDto {
    private Long id;
   private List<Date> time;
    private Date date;
    private String cinema;
    private String movieName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Date> getTime() {
        return time;
    }

    public void setTime(List<Date> time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
