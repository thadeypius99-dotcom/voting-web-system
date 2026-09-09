package com.voting.voting_system.repository;


import com.voting.voting_system.entity.Candidate;
import com.voting.voting_system.entity.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {


    // Check if a user has already voted
    boolean existsByUser_UserId(int userId);



    @Query("""
            SELECT 
            c.candidateId,
            c.candidateName,
            c.partyName,
            COUNT(v.voteId)

            FROM Candidate c

            LEFT JOIN Vote v
            ON c.candidateId = v.candidate.candidateId

            GROUP BY 
            c.candidateId,
            c.candidateName,
            c.partyName
            """)
    List<Object[]> countVotesByCandidate();


}