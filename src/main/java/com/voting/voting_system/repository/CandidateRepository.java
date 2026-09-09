package com.voting.voting_system.repository;

import com.voting.voting_system.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    // Get candidates belonging to a specific election
    List<Candidate> findByElection_ElectionId(int electionId);

}