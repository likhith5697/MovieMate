package com.movieBookingSystem.mbsystem.repositories;

import java.util.Optional;

import com.movieBookingSystem.mbsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailId(String emailId);;
}