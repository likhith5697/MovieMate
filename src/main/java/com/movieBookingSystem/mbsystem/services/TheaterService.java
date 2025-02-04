package com.movieBookingSystem.mbsystem.services;


import com.movieBookingSystem.mbsystem.convertors.TheatreConvertor;
import com.movieBookingSystem.mbsystem.enums.SeatType;
import com.movieBookingSystem.mbsystem.exceptions.TheatreExist;
import com.movieBookingSystem.mbsystem.exceptions.TheatreNotExist;
import com.movieBookingSystem.mbsystem.models.Theatre;
import com.movieBookingSystem.mbsystem.models.TheatreSeat;
import com.movieBookingSystem.mbsystem.repositories.TheaterRepository;
import com.movieBookingSystem.mbsystem.request.TheaterSeatRequest;
import com.movieBookingSystem.mbsystem.request.TheatreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;


    public String addTheatre(TheatreRequest theatreRequest) throws TheatreExist{
        if(theaterRepository.findByAddress(theatreRequest.getAddress())!=null){
            throw new TheatreExist();
        }
        Theatre theatre = TheatreConvertor.theaterDtoToTheater(theatreRequest);

        theaterRepository.save(theatre);
        return "Theatre has been saved successfully";
    }

    public String addTheatreSeat(TheaterSeatRequest theaterSeatRequest){
        if(theaterRepository.findByAddress(theaterSeatRequest.getAddress())==null){
            throw new TheatreNotExist();
        }

        Integer numOfSeatsInRow = theaterSeatRequest.getNoOfSeatInRow();
        Integer numOfPremiumSeats = theaterSeatRequest.getNoOfPremiumSeat();
        Integer NumOfClassicSeats = theaterSeatRequest.getNoOfClassicSeat();
        String address = theaterSeatRequest.getAddress();
        Theatre thatre = theaterRepository.findByAddress(address);

        List<TheatreSeat> seatList = thatre.getTheaterSeatList();

        int counter =1;
        int fill = 0;
        char ch= 'A';

        for(int i=0;i<=NumOfClassicSeats;i++){
            String seatNum = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if(fill == numOfSeatsInRow){
                fill = 0;
                counter++;
                ch='A';
            }
            TheatreSeat theatreSeat = new TheatreSeat();
            theatreSeat.setSeatNo(seatNum);
            theatreSeat.setSeatType(SeatType.CLASSIC);
            theatreSeat.setTheatre(thatre);
            seatList.add(theatreSeat);
        }

        for(int i=1;i<=numOfPremiumSeats;i++){
            String seatum = Integer.toString(counter) + ch;
            ch++;
            fill++;

            if(fill == numOfSeatsInRow){
                fill = 0;
                counter++;
                ch='A';
            }
            TheatreSeat theatreSeat = new TheatreSeat();
            theatreSeat.setSeatNo(seatum);
            theatreSeat.setSeatType(SeatType.PREMIUM);
            theatreSeat.setTheatre(thatre);
            seatList.add(theatreSeat);
        }
        theaterRepository.save(thatre);

        return "Theaters have been added successfuly";
    }


}
