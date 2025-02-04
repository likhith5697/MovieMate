package com.movieBookingSystem.mbsystem.repositories;

import com.movieBookingSystem.mbsystem.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByMovieName(String name);
}