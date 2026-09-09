package com.voting.voting_system.controller;

import com.voting.voting_system.dto.AuthResponse;
import com.voting.voting_system.dto.LoginDTO;
import com.voting.voting_system.entity.User;
import com.voting.voting_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // ==============================
    // GET ALL USERS
    // ==============================

    @GetMapping
    public List<User> getUsers() {

        return userService.getAllUsers();
    }


    // ==============================
    // GET USER BY ID
    // ==============================

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {

        return userService.getUserById(id)
                .orElse(null);
    }


    // ==============================
    // REGISTER VOTER
    // ==============================

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return userService.registerUser(user);
    }


    // ==============================
    // ADD USER
    // ==============================

    @PostMapping
    public User addUser(@RequestBody User user) {

        return userService.saveUser(user);
    }


    // ==============================
    // DELETE USER
    // ==============================

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser(id);

        return "User deleted successfully";
    }


    // ==============================
    // LOGIN
    // ==============================

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginDTO loginDTO) {

        User user = userService.login(
                loginDTO.getEmail(),
                loginDTO.getPassword()
        );

        return new AuthResponse(
                "Login successful",
                user.getUserId(),
                user.getRole()
        );
    }

}