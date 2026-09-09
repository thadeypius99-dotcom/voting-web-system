package com.voting.voting_system.service;

import com.voting.voting_system.entity.Candidate;
import com.voting.voting_system.entity.Election;
import com.voting.voting_system.repository.CandidateRepository;
import com.voting.voting_system.repository.ElectionRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final ElectionRepository electionRepository;


    public CandidateService(
            CandidateRepository candidateRepository,
            ElectionRepository electionRepository) {

        this.candidateRepository = candidateRepository;
        this.electionRepository = electionRepository;
    }


    // Get all candidates
    public List<Candidate> getAllCandidates() {

        return candidateRepository.findAll();

    }


    // Get candidates belonging to a specific election
    public List<Candidate> getCandidatesByElection(int electionId) {

        // Check that the election exists
        electionRepository.findById(electionId)
                .orElseThrow(() ->
                        new RuntimeException("Election not found"));

        // Get candidates for this election
        return candidateRepository
                .findByElection_ElectionId(electionId);

    }


    // Get candidate by ID
    public Optional<Candidate> getCandidateById(int id) {

        return candidateRepository.findById(id);

    }


    // Save candidate
    public Candidate saveCandidate(Candidate candidate) {

        System.out.println("========== DEBUG ==========");

        System.out.println(
                "Candidate Name: "
                + candidate.getCandidateName()
        );

        System.out.println(
                "Party: "
                + candidate.getPartyName()
        );

        System.out.println(
                "Position: "
                + candidate.getPosition()
        );


        if (candidate.getElection() == null) {

            System.out.println("Election is NULL");

        } else {

            System.out.println(
                    "Election ID received: "
                    + candidate.getElection().getElectionId()
            );


            Election election = electionRepository
                    .findById(
                            candidate.getElection().getElectionId()
                    )
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Election not found"
                            )
                    );


            System.out.println(
                    "Election found in DB: "
                    + election.getElectionName()
            );


            candidate.setElection(election);
        }


        Candidate savedCandidate =
                candidateRepository.save(candidate);


        System.out.println(
                "Candidate saved successfully!"
        );

        System.out.println(
                "==========================="
        );


        return savedCandidate;
    }


    // Delete candidate
    public void deleteCandidate(int id) {

        candidateRepository.deleteById(id);

    }

}