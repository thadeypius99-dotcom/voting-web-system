package com.voting.voting_system.service;

import com.voting.voting_system.entity.Election;
import com.voting.voting_system.repository.ElectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionService {

    private final ElectionRepository electionRepository;


    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }


    // Get all elections
    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }


    // Get election by ID
    public Optional<Election> getElectionById(int id) {
        return electionRepository.findById(id);
    }


    // Save election
    public Election saveElection(Election election) {
        return electionRepository.save(election);
    }


    // Delete election
    public void deleteElection(int id) {
        electionRepository.deleteById(id);
    }
}
