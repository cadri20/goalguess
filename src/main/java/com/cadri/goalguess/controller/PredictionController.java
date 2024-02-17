package com.cadri.goalguess.controller;

import com.cadri.goalguess.dto.PointsDTO;
import com.cadri.goalguess.service.MatchdayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predictions")
public class PredictionController {
    private MatchdayService matchdayService;

    public PredictionController(MatchdayService matchdayService) {
        this.matchdayService = matchdayService;
    }

    @GetMapping("/{predictionId}/points")
    public PointsDTO getPredictionPoints(@PathVariable Long predictionId) {
        return new PointsDTO(matchdayService.getPredictionPoints(predictionId));
    }
}
