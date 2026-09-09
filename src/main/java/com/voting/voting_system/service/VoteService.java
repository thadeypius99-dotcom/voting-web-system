package com.voting.voting_system.service;

import com.voting.voting_system.entity.Candidate;
import com.voting.voting_system.entity.User;
import com.voting.voting_system.entity.Vote;
import com.voting.voting_system.repository.CandidateRepository;
import com.voting.voting_system.repository.UserRepository;
import com.voting.voting_system.repository.VoteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;

    public VoteService(
            VoteRepository voteRepository,
            UserRepository userRepository,
            CandidateRepository candidateRepository) {

        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public Vote castVote(Vote vote) {

        System.out.println("========== DEBUG START ==========");

        // Get user
        User user = userRepository.findById(vote.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("STEP 1: User found");
        System.out.println("User ID: " + user.getUserId());
        System.out.println("User Name: " + user.getFullName());
        System.out.println("Current hasVoted: " + user.isHasVoted());

        // Check if already voted
        // Check if user already voted
if (voteRepository.existsByUser_UserId(user.getUserId())) {
    throw new RuntimeException("User has already voted");
}
        // Get candidate
        Candidate candidate = candidateRepository.findById(vote.getCandidate().getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        System.out.println("STEP 2: Candidate found");
        System.out.println("Candidate: " + candidate.getCandidateName());

        // Prepare vote
        vote.setUser(user);
        vote.setCandidate(candidate);
        vote.setVoteDate(LocalDateTime.now());

        // Save vote
        Vote savedVote = voteRepository.save(vote);

        System.out.println("STEP 3: Vote saved successfully");

        // Update user status
        user.setHasVoted(true);

        System.out.println("STEP 4: User object updated");
        System.out.println("Current hasVoted: " + user.isHasVoted());

        // Save user
        userRepository.saveAndFlush(user);

        System.out.println("STEP 5: User saved to database");

        // Read user again from database
        User updatedUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User disappeared"));

        System.out.println("STEP 6: Reloaded from database");
        System.out.println("Database hasVoted: " + updatedUser.isHasVoted());

        System.out.println("=========== DEBUG END ===========");

        return savedVote;
    }

    public Iterable<Vote> getAllVotes() {
        return voteRepository.findAll();
    }
}