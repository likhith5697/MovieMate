package com.movieBookingSystem.mbsystem.exceptions;

public class ShowDoesNotExist extends RuntimeException{

    public ShowDoesNotExist(){
        super("Show does not exist");
    }
}
