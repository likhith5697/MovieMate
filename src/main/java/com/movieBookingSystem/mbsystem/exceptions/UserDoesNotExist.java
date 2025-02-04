package com.movieBookingSystem.mbsystem.exceptions;

public class UserDoesNotExist extends RuntimeException{
    public UserDoesNotExist(){
        super("User does not exist");
    }
}
