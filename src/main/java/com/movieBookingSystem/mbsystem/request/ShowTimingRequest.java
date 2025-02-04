package com.movieBookingSystem.mbsystem.request;


import lombok.Data;

import java.sql.Date;

@Data
public class ShowTimingRequest {
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    private Date date;
    private Integer theaterId;
    private Integer movieId;
}
