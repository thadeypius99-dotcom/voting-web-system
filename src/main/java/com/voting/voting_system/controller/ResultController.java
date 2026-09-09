package com.voting.voting_system.controller;

import com.voting.voting_system.dto.ResultDTO;
import com.voting.voting_system.service.ResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {


    private final ResultService resultService;


    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }


    @GetMapping
    public List<ResultDTO> getResults() {

        return resultService.getElectionResults();

    }


    @GetMapping("/winner")
    public ResultDTO getWinner() {

        return resultService.getWinner();

    }
}