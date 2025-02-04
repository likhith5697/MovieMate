package com.movieBookingSystem.mbsystem.services;


import com.movieBookingSystem.mbsystem.convertors.MovieConvertor;
import com.movieBookingSystem.mbsystem.exceptions.MovieAlreadyExist;
import com.movieBookingSystem.mbsystem.models.Movie;
import com.movieBookingSystem.mbsystem.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jts.movie.request.MovieRequest;

@Service
public class MovieService {


    @Autowired
    private MovieRepository movieRepo;

    public String addMovie(MovieRequest movieRequest){
        Movie movieByname = movieRepo.findByMovieName(movieRequest.getMovieName());
        if(movieByname != null && movieByname.getLanguage().equals(movieRequest.getLanguage())){
            throw new MovieAlreadyExist();
        }

        Movie movie = MovieConvertor.movieDtoToMovie(movieRequest);
        movieRepo.save(movie);
        return "The Movie has been added successfully";
    }
}
