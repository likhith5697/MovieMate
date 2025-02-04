package com.movieBookingSystem.mbsystem.convertors;


import com.movieBookingSystem.mbsystem.models.Theatre;
import com.movieBookingSystem.mbsystem.request.TheatreRequest;

public class TheatreConvertor {

    public static Theatre theaterDtoToTheater(TheatreRequest theaterRequest) {
        Theatre theatre = Theatre.builder()
                .name(theaterRequest.getName())
                .address(theaterRequest.getAddress())
                .build();
        return theatre;
    }
}
