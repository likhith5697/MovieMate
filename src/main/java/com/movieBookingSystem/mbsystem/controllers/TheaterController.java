package com.movieBookingSystem.mbsystem.controllers;


import com.movieBookingSystem.mbsystem.request.TheaterSeatRequest;
import com.movieBookingSystem.mbsystem.request.TheatreRequest;
import com.movieBookingSystem.mbsystem.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addNewTheater")
    public ResponseEntity<String> addTheater(@RequestBody TheatreRequest request) {
        try {
            String result = theaterService.addTheatre(request);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addTheaterSeat")
    public ResponseEntity<String> addTheaterSeat(@RequestBody TheaterSeatRequest theaterSeatRequest) {
        try {
            String result = theaterService.addTheatreSeat(theaterSeatRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}