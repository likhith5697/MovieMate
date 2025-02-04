package com.movieBookingSystem.mbsystem.exceptions;

public class TheatreExist extends RuntimeException{
    public TheatreExist(){
        super("Theatre already exists");
    }
}
