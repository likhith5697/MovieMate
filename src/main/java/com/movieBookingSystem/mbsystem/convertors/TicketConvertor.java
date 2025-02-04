package com.movieBookingSystem.mbsystem.convertors;


import com.movieBookingSystem.mbsystem.models.Show;
import com.movieBookingSystem.mbsystem.models.Ticket;
import com.movieBookingSystem.mbsystem.response.TicketResponse;

public class TicketConvertor {

    public static TicketResponse returnTicket(Show show, Ticket ticket) {
        TicketResponse ticketResponseDto = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .address(show.getTheatre().getAddress())
                .theaterName(show.getTheatre().getName())
                .movieName(show.getMovie().getMovieName())
                .date(show.getDate())
                .time(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();

        return ticketResponseDto;
    }
}