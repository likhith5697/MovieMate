package com.movieBookingSystem.mbsystem.exceptions;

public class MovieAlreadyExist extends RuntimeException {
    private static final long serialVersionUID = -4666349320340656440L;
    public MovieAlreadyExist(){
        super("Movie alraedy exists");
    }
}
