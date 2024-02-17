package com.cadri.goalguess.controller;

import com.cadri.goalguess.dto.MatchResultDTO;
import com.cadri.goalguess.dto.MatchdayResultRequestDTO;
import com.cadri.goalguess.dto.PredictionDTO;
import com.cadri.goalguess.dto.PredictionRequestDTO;
import com.cadri.goalguess.model.MatchdayResult;
import com.cadri.goalguess.service.MatchdayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/matchdays")
public class MatchdayController {
    private MatchdayService matchdayService;


    public MatchdayController(MatchdayService matchdayService) {
        this.matchdayService = matchdayService;
    }
    @PostMapping("/{matchdayId}/results")
    public MatchdayResult createMatchdayResults(@PathVariable Long matchdayId, @RequestBody MatchdayResultRequestDTO matchdayResultRequestDTO) {
        return matchdayService.saveMatchdayResult(matchdayId, matchdayResultRequestDTO);
    }

    @GetMapping("/{matchdayId}/results")
    public List<MatchResultDTO> getMatchdayResults(@PathVariable Long matchdayId) {
        return matchdayService.getMatchdayResults(matchdayId);
    }

    @PostMapping("/{matchdayId}/predictions")
    public PredictionDTO createPrediction(@PathVariable Long matchdayId, @RequestBody PredictionRequestDTO prediction) {
        return matchdayService.savePrediction(matchdayId, prediction);
    }

    @GetMapping("/{matchdayId}/predictions")
    public List<PredictionDTO> getPredictions(@PathVariable Long matchdayId) {
        return matchdayService.getPredictions(matchdayId);
    }

}
