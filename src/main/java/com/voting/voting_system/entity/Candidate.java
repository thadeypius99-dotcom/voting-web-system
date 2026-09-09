package com.voting.voting_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;

    private String candidateName;

    private String partyName;

    private String position;


    // Relationship with Election
    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;


    // Empty constructor
    public Candidate() {
    }


    // Constructor
    public Candidate(String candidateName, String partyName, String position) {

        this.candidateName = candidateName;
        this.partyName = partyName;
        this.position = position;

    }


    // Getters and Setters

    public int getCandidateId() {
        return candidateId;
    }


    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }


    public String getCandidateName() {
        return candidateName;
    }


    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }


    public String getPartyName() {
        return partyName;
    }


    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }


    public String getPosition() {
        return position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public Election getElection() {
        return election;
    }


    public void setElection(Election election) {
        this.election = election;
    }

}