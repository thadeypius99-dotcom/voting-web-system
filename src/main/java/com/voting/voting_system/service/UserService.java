package com.voting.voting_system.service;

import com.voting.voting_system.entity.User;
import com.voting.voting_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // ==============================
    // GET ALL USERS
    // ==============================

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }


    // ==============================
    // GET USER BY ID
    // ==============================

    public Optional<User> getUserById(int id) {

        return userRepository.findById(id);
    }


    // ==============================
    // SAVE USER
    // ==============================

    public User saveUser(User user) {

        return userRepository.save(user);
    }


    // ==============================
    // REGISTER VOTER
    // ==============================

    public User registerUser(User user) {

        // Check whether email already exists

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {

            throw new RuntimeException("Email already registered");

        }


        // Every person registering through
        // the registration page becomes a VOTER

        user.setRole("VOTER");


        // New voter has not voted yet

        user.setHasVoted(false);


        return userRepository.save(user);
    }


    // ==============================
    // DELETE USER
    // ==============================

    public void deleteUser(int id) {

        userRepository.deleteById(id);
    }


    // ==============================
    // LOGIN
    // ==============================

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);


        if (user == null) {

            throw new RuntimeException("User not found");
        }


        if (!user.getPassword().equals(password)) {

            throw new RuntimeException("Invalid password");
        }


        return user;
    }

}