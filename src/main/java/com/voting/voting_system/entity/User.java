package com.voting.voting_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;


    private String fullName;


    private String email;


    // Password can be received from frontend
    // but will NOT be returned to frontend
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    private String role;


    @Column(name = "has_voted")
    private Boolean hasVoted = false;



    // Empty constructor
    public User() {
    }



    // Constructor
    public User(String fullName, String email, String password,
                String role, Boolean hasVoted) {

        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.hasVoted = hasVoted;

    }



    // Getters and Setters

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public Boolean isHasVoted() {
        return hasVoted;
    }


    public void setHasVoted(Boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}