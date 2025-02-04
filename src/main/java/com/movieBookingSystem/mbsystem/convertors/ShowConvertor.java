package com.movieBookingSystem.mbsystem.convertors;


import com.movieBookingSystem.mbsystem.models.Show;
import com.movieBookingSystem.mbsystem.request.ShowRequest;

public class ShowConvertor {

    public static Show showDtoToShow(ShowRequest showRequest) {
        Show show = Show.builder()
                .time(showRequest.getShowStartTime())
                .date(showRequest.getShowDate())
                .build();

        return show;
    }
}
