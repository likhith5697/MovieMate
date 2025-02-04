package com.movieBookingSystem.mbsystem.services;


import com.movieBookingSystem.mbsystem.convertors.UserConvertor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import com.movieBookingSystem.mbsystem.exceptions.UserExist;
import com.movieBookingSystem.mbsystem.models.User;
import com.movieBookingSystem.mbsystem.repositories.UserRepository;
import com.movieBookingSystem.mbsystem.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserRequest userRequest){
        Optional<User> users  = userRepository.findByEmailId(userRequest.getEmailId());
        if(users.isPresent()){
            throw new UserExist();
        }
        User user = UserConvertor.userDtoToUser(userRequest,passwordEncoder.encode(("1234")));
    userRepository.save(user);
    return "User created successfully";
    }
}
