package com.voting.voting_system.controller;

import com.voting.voting_system.entity.Election;
import com.voting.voting_system.service.ElectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elections")
public class ElectionController {


    private final ElectionService electionService;


    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }


    // Get all elections
    @GetMapping
    public List<Election> getElections() {

        return electionService.getAllElections();
    }


    // Get election by ID
    @GetMapping("/{id}")
    public Election getElectionById(@PathVariable int id) {

        return electionService.getElectionById(id)
                .orElse(null);
    }


    // Create election (ADMIN ONLY)
    @PostMapping
    public Election addElection(
            @RequestHeader("X-ROLE") String role,
            @RequestBody Election election) {


        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Access denied. Admin only.");
        }


        return electionService.saveElection(election);
    }


    // Delete election (ADMIN ONLY)
    @DeleteMapping("/{id}")
    public String deleteElection(
            @RequestHeader("X-ROLE") String role,
            @PathVariable int id) {


        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Access denied. Admin only.");
        }


        electionService.deleteElection(id);

        return "Election deleted successfully";
    }
}