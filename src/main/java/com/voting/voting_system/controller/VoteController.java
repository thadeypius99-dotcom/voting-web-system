package com.voting.voting_system.controller;

import com.voting.voting_system.entity.Vote;
import com.voting.voting_system.service.VoteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
public class VoteController {


    private final VoteService voteService;


    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }


    // Cast a vote (VOTER ONLY)
    @PostMapping
    public Vote castVote(
            @RequestHeader("X-ROLE") String role,
            @RequestBody Vote vote) {


        if (!role.equals("VOTER")) {
            throw new RuntimeException("Only voters can vote.");
        }


        return voteService.castVote(vote);
    }


    // Get all votes
    @GetMapping
    public Iterable<Vote> getAllVotes() {

        return voteService.getAllVotes();
    }
}