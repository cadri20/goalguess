package com.cadri.goalguess.controller;

import com.cadri.goalguess.dto.MatchResultDTO;
import com.cadri.goalguess.dto.MatchdayResultRequestDTO;
import com.cadri.goalguess.service.MatchdayService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/matchdays")
public class MatchdayController {
    private MatchdayService matchdayService;

    public MatchdayController(MatchdayService matchdayService) {
        this.matchdayService = matchdayService;
    }
    @PostMapping("/{matchdayId}/results")
    public void createMatchdayResults(@PathVariable Long matchdayId, @RequestBody MatchdayResultRequestDTO matchdayResultRequestDTO) {
        matchdayService.saveMatchdayResult(matchdayId, matchdayResultRequestDTO);
    }
}
