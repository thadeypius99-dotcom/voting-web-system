package com.voting.voting_system.dto;


public class AuthResponse {

    private String message;

    private int userId;

    private String role;


    // Empty constructor
    public AuthResponse() {
    }


    // Constructor
    public AuthResponse(String message, int userId, String role) {
        this.message = message;
        this.userId = userId;
        this.role = role;
    }


    // Getters and Setters

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }
}