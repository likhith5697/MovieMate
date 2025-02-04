package com.movieBookingSystem.mbsystem.exceptions;

public class MovieDoesNotExist extends RuntimeException{
    public MovieDoesNotExist(){
        super("Movie Does Not Exist");
    }
}
