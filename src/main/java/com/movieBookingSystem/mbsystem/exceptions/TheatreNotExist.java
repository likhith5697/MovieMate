package com.movieBookingSystem.mbsystem.exceptions;

public class TheatreNotExist extends RuntimeException{
    public TheatreNotExist(){
        super("Theater does not exist");
    }
}
