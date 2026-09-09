package com.voting.voting_system.service;


import com.voting.voting_system.dto.ResultDTO;
import com.voting.voting_system.repository.VoteRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ResultService {


    private final VoteRepository voteRepository;



    public ResultService(VoteRepository voteRepository) {

        this.voteRepository = voteRepository;

    }



    public List<ResultDTO> getElectionResults() {


        List<Object[]> results =
                voteRepository.countVotesByCandidate();



        List<ResultDTO> resultDTOList =
                new ArrayList<>();



        for(Object[] result : results) {


            ResultDTO dto = new ResultDTO(

                    (Integer) result[0],
                    (String) result[1],
                    (String) result[2],
                    (Long) result[3]

            );


            resultDTOList.add(dto);

        }



        return resultDTOList;

    }




    public ResultDTO getWinner() {


        List<ResultDTO> results =
                getElectionResults();



        if(results.isEmpty()) {

            throw new RuntimeException("No votes found");

        }



        ResultDTO winner = results.get(0);



        for(ResultDTO result : results) {


            if(result.getTotalVotes() >
                    winner.getTotalVotes()) {


                winner = result;

            }

        }



        return winner;

    }

}