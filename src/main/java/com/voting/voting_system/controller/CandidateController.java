package com.voting.voting_system.controller;

import com.voting.voting_system.entity.Candidate;
import com.voting.voting_system.service.CandidateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;


    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }


    // Get all candidates
    @GetMapping
    public List<Candidate> getCandidates() {

        return candidateService.getAllCandidates();

    }


    // Get candidates belonging to a specific election
    @GetMapping("/election/{electionId}")
    public List<Candidate> getCandidatesByElection(
            @PathVariable int electionId) {

        return candidateService.getCandidatesByElection(electionId);

    }


    // Get candidate by ID
    @GetMapping("/{id}")
    public Candidate getCandidateById(@PathVariable int id) {

        return candidateService.getCandidateById(id)
                .orElse(null);

    }


    // Add candidate (ADMIN ONLY)
    @PostMapping
    public Candidate addCandidate(
            @RequestHeader("X-ROLE") String role,
            @RequestBody Candidate candidate) {

        if (!role.equals("ADMIN")) {

            throw new RuntimeException(
                    "Access denied. Admin only."
            );

        }

        return candidateService.saveCandidate(candidate);

    }


    // Delete candidate (ADMIN ONLY)
    @DeleteMapping("/{id}")
    public String deleteCandidate(
            @RequestHeader("X-ROLE") String role,
            @PathVariable int id) {

        if (!role.equals("ADMIN")) {

            throw new RuntimeException(
                    "Access denied. Admin only."
            );

        }

        candidateService.deleteCandidate(id);

        return "Candidate deleted successfully";

    }

}