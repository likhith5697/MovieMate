package com.movieBookingSystem.mbsystem.services;


import com.movieBookingSystem.mbsystem.convertors.TicketConvertor;
import com.movieBookingSystem.mbsystem.exceptions.SeatNotAvailable;
import com.movieBookingSystem.mbsystem.exceptions.ShowDoesNotExist;
import com.movieBookingSystem.mbsystem.exceptions.UserDoesNotExist;
import com.movieBookingSystem.mbsystem.models.Show;
import com.movieBookingSystem.mbsystem.models.ShowSeat;
import com.movieBookingSystem.mbsystem.models.Ticket;
import com.movieBookingSystem.mbsystem.models.User;
import com.movieBookingSystem.mbsystem.repositories.ShowRepository;
import com.movieBookingSystem.mbsystem.repositories.TicketRepository;
import com.movieBookingSystem.mbsystem.repositories.UserRepository;
import com.movieBookingSystem.mbsystem.request.TicketRequest;
import com.movieBookingSystem.mbsystem.response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    public TicketResponse ticketBooking (TicketRequest ticketRequest){

        Optional<Show> showOptional = showRepository.findById(ticketRequest.getShowId());

        if(showOptional.isEmpty()){
            throw new ShowDoesNotExist();
        }

        Optional<User> userOptional = userRepository.findById(ticketRequest.getUserId());

        if(userOptional.isEmpty()){
            throw new UserDoesNotExist();
        }

        User user = userOptional.get();
        Show show = showOptional.get();

        Boolean isSeatAvailable = isSeatAvaiable(show.getShowSeatList(),ticketRequest.getRequestSeats());

        if(!isSeatAvailable){
            throw new SeatNotAvailable();
        }

        Integer getPriceAndAssignSeats = getPriceAndAssignSeats(show.getShowSeatList(),ticketRequest.getRequestSeats());
        String seats = listToString(ticketRequest.getRequestSeats());

        //book tickets now

        Ticket ticket = new Ticket();
        ticket.setTotalTicketsPrice(getPriceAndAssignSeats);
        ticket.setBookedSeats(seats);
        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        // add this ticket to user profile record and show record

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);
        userRepository.save(user);
        showRepository.save(show);


        return TicketConvertor.returnTicket(show,ticket);

    }

    private boolean isSeatAvaiable(List<ShowSeat> showSeatList,List<String> requestedSeats){
        for (ShowSeat showSeat:showSeatList){
            String seatNo = showSeat.getSeatNo();
            if(requestedSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
                return false;
            }
        }
        return true;
    }

    private Integer getPriceAndAssignSeats(List<ShowSeat>showSeatList,List<String> requestedSeats){
        Integer totalCost = 0;

        for(ShowSeat showSeat:showSeatList){
            totalCost += showSeat.getPrice();
            showSeat.setIsAvailable(Boolean.FALSE);
        }
        return totalCost;
    }


    private String listToString(List<String> requestSeats) {
        StringBuilder sb = new StringBuilder();

        for (String s : requestSeats) {
            sb.append(s).append(",");
        }

        return sb.toString();
    }


}
