package com.movieBookingSystem.mbsystem.repositories;

import com.movieBookingSystem.mbsystem.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}