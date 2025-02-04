package com.movieBookingSystem.mbsystem.convertors;


import com.jts.movie.request.MovieRequest;
import com.movieBookingSystem.mbsystem.models.Movie;

public class MovieConvertor {

    public static Movie movieDtoToMovie(MovieRequest movieRequest){
        Movie movie = Movie.builder().movieName(movieRequest.getMovieName())
                .duration(movieRequest.getDuration())
                .genre(movieRequest.getGenre())
                .language(movieRequest.getLanguage())
                .rating(movieRequest.getRating())
                .releaseDate(movieRequest.getReleaseDate())
                .build();

        return movie;
    }
}
