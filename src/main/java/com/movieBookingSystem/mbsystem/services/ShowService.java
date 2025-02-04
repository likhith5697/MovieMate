package com.movieBookingSystem.mbsystem.services;


import com.movieBookingSystem.mbsystem.convertors.ShowConvertor;
import com.movieBookingSystem.mbsystem.enums.SeatType;
import com.movieBookingSystem.mbsystem.exceptions.MovieDoesNotExist;
import com.movieBookingSystem.mbsystem.exceptions.ShowDoesNotExist;
import com.movieBookingSystem.mbsystem.exceptions.TheatreNotExist;
import com.movieBookingSystem.mbsystem.models.*;
import com.movieBookingSystem.mbsystem.repositories.MovieRepository;
import com.movieBookingSystem.mbsystem.repositories.ShowRepository;
import com.movieBookingSystem.mbsystem.repositories.TheaterRepository;
import com.movieBookingSystem.mbsystem.request.ShowRequest;
import com.movieBookingSystem.mbsystem.request.ShowSeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;



    //Book a show, example book a show at 6:00pm at Cinemark for OG movie
    public String addShow(ShowRequest showRequest){
        Show show = ShowConvertor.showDtoToShow(showRequest);

        Optional<Movie> optionalMovie = movieRepository.findById(showRequest.getMovieId());
        if(optionalMovie.isEmpty()){
            throw new MovieDoesNotExist();
        }

        Optional<Theatre> theatreOptional = theaterRepository.findById(showRequest.getTheaterId());
        if(theatreOptional.isEmpty()){
            throw new TheatreNotExist();
        }

        Theatre thatre = theatreOptional.get();
        Movie movie = optionalMovie.get();
        show.setMovie(movie);
        show.setTheatre(thatre);
        show = showRepository.save(show);

        movie.getShows().add(show);
        thatre.getShowList().add(show);
        movieRepository.save(movie);
        theaterRepository.save(thatre);
        return "Theater has been added successfully";
    }

    public String associateShowSeats(ShowSeatRequest showSeatRequest) {
        Optional<Show> showOptional = showRepository.findById(showSeatRequest.getShowId());
        if(showOptional.isEmpty()){
            throw new ShowDoesNotExist();
        }

        Show show = showOptional.get();
        Theatre theatre = show.getTheatre();
        List<TheatreSeat> theatreSeatList = theatre.getTheaterSeatList();
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(TheatreSeat theatreSeat:theatreSeatList){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theatreSeat.getSeatNo());
            showSeat.setSeatType(theatreSeat.getSeatType());

            if(!showSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatRequest.getPriceOfClassicSeat());
            }
            else{
                showSeat.setPrice(showSeatRequest.getPriceOfPremiumSeat());
            }

            showSeat.setShow(show);
            showSeat.setIsAvailable(Boolean.TRUE);
            showSeat.setIsFoodContains(Boolean.FALSE);

            showSeatList.add(showSeat);
        }
        showRepository.save(show);
        return "Show seats have been selected successfully";

    }

}
