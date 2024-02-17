package com.cadri.goalguess.service;

import com.cadri.goalguess.model.MatchResult;
import com.cadri.goalguess.model.MatchdayResult;
import com.cadri.goalguess.model.Prediction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {
    public double getPredictionPoints(Prediction prediction, MatchdayResult matchdayResult){
        List<MatchResult> results = matchdayResult.getResults();
        List<MatchResult> predictions = prediction.getResults();
        double points = 0;
        for(MatchResult result: results){
            var match = result.getMatch();
            var predictionResult = predictions.stream().filter(p -> p.getMatch().equals(match)).findFirst().orElse(null);
            if(predictionResult != null){
                points += result.compare(predictionResult);
            }
        }
        return points / results.size() * 100;
    }
}
