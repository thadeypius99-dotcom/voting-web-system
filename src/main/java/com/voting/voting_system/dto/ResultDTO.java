package com.voting.voting_system.dto;


public class ResultDTO {

    private int candidateId;

    private String candidateName;

    private String partyName;

    private long totalVotes;


    public ResultDTO(int candidateId, String candidateName,
                     String partyName, long totalVotes) {

        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.partyName = partyName;
        this.totalVotes = totalVotes;
    }


    public int getCandidateId() {
        return candidateId;
    }


    public String getCandidateName() {
        return candidateName;
    }


    public String getPartyName() {
        return partyName;
    }


    public long getTotalVotes() {
        return totalVotes;
    }
}