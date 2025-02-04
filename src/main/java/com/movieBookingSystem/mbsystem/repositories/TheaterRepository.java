package com.movieBookingSystem.mbsystem.repositories;

import com.movieBookingSystem.mbsystem.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TheaterRepository extends JpaRepository<Theatre, Integer> {
    Theatre findByAddress(String address);
}