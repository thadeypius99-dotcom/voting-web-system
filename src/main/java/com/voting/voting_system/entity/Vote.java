package com.voting.voting_system.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;


    // Relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Relationship with Candidate
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;


    private LocalDateTime voteDate;


    // Empty constructor
    public Vote() {
    }


    // Constructor
    public Vote(User user, Candidate candidate) {
        this.user = user;
        this.candidate = candidate;
        this.voteDate = LocalDateTime.now();
    }


    // Getters and Setters

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }


    public LocalDateTime getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDateTime voteDate) {
        this.voteDate = voteDate;
    }
}