package com.movieBookingSystem.mbsystem.exceptions;

public class SeatNotAvailable extends RuntimeException{
    public SeatNotAvailable(){
        super("Seats are not available");
    }
}
