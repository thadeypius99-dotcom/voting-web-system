package com.voting.voting_system.repository;

import com.voting.voting_system.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Integer> {

}