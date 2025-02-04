package com.movieBookingSystem.mbsystem.controllers;



import com.movieBookingSystem.mbsystem.config.JWTService;
import com.movieBookingSystem.mbsystem.request.UserRequest;
import com.movieBookingSystem.mbsystem.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.movieBookingSystem.mbsystem.services.userService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private userService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/addNewUser")
    public ResponseEntity<String> addNewUser (@RequestBody UserRequest userRequest){
        try{
            String result = userService.addUser(userRequest);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }
        throw new UsernameNotFoundException("invalid user details.");
    }

}