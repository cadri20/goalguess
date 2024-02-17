package com.cadri.goalguess.service;

import com.cadri.goalguess.dto.*;
import com.cadri.goalguess.exception.RestException;
import com.cadri.goalguess.model.Matchday;
import com.cadri.goalguess.model.MatchdayResult;
import com.cadri.goalguess.model.Prediction;
import com.cadri.goalguess.repository.MatchdayRepository;
import com.cadri.goalguess.repository.PredictionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class MatchdayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchdayService.class);
    private MatchdayRepository matchdayRepository;

    private PredictionRepository predictionRepository;

    private ConversionService conversionService;

    private PointService pointService;

    public MatchdayService(MatchdayRepository matchdayRepository,
                           PredictionRepository predictionRepository,
                           ConversionService conversionService,
                            PointService pointService
    ) {
        this.matchdayRepository = matchdayRepository;
        this.conversionService = conversionService;
        this.predictionRepository = predictionRepository;
        this.pointService = pointService;
    }

    public MatchdayDTO findNextMatchday(Long leagueId){
        Matchday matchday = matchdayRepository.findTopByLeagueIdOrderByDateDesc(leagueId).orElse(null);
        if(matchday == null){
            LOGGER.debug("Not exist matchday for the league with the id {}", leagueId);
            throw new RestException(HttpStatus.NOT_FOUND, "Matchday not found");
        }
        return conversionService.convert(matchday, MatchdayDTO.class);
    }

    public MatchdayResult saveMatchdayResult(Long matchdayId, MatchdayResultRequestDTO matchdayResult){
        Matchday matchday = matchdayRepository.findById(matchdayId).orElse(null);
        if(matchday == null){
            LOGGER.debug("Not exist matchday with the id {}", matchdayId);
            throw new RestException(HttpStatus.NOT_FOUND, "Matchday not found");
        }
        MatchdayResult transformed = conversionService.convert(matchdayResult, MatchdayResult.class);
        matchday.setMatchdayResult(transformed);
        return matchdayRepository.save(matchday).getMatchdayResult();
    }

    public List<MatchResultDTO> getMatchdayResults(Long matchdayId){
        Matchday matchday = matchdayRepository.findById(matchdayId).orElse(null);
        if(matchday == null){
            LOGGER.debug("Not exist matchday with the id {}", matchdayId);
            throw new RestException(HttpStatus.NOT_FOUND, "Matchday not found");
        }
        return conversionService.convert(matchday.getMatchdayResult().getResults(), List.class);
    }

    public PredictionDTO savePrediction(Long matchdayId, PredictionRequestDTO prediction){
        Matchday matchday = matchdayRepository.findById(matchdayId).orElse(null);
        if(matchday == null){
            LOGGER.debug("Not exist matchday with the id {}", matchdayId);
            throw new RestException(HttpStatus.NOT_FOUND, "Matchday not found");
        }

        Prediction transformed = conversionService.convert(prediction, Prediction.class);
        LOGGER.debug("Transformed prediction: {}", transformed);
        transformed.setMatchday(matchday);
        Prediction created = predictionRepository.save(transformed);
        return conversionService.convert(created, PredictionDTO.class);
    }

    public List<PredictionDTO> getPredictions(Long matchdayId){
        Matchday matchday = matchdayRepository.findById(matchdayId).orElse(null);
        if(matchday == null){
            LOGGER.debug("Not exist matchday with the id {}", matchdayId);
            throw new RestException(HttpStatus.NOT_FOUND, "Matchday not found");
        }
        return conversionService.convert(matchday.getPredictions(), List.class);
    }

    public double getPredictionPoints(Long predictionId){
        Prediction prediction = predictionRepository.findById(predictionId).orElse(null);
        if(prediction == null){
            LOGGER.debug("Not exist prediction with the id {}", predictionId);
            throw new RestException(HttpStatus.NOT_FOUND, "Prediction not found");
        }

        Matchday matchday = prediction.getMatchday();
        MatchdayResult matchdayResult = matchday.getMatchdayResult();

        return pointService.getPredictionPoints(prediction, matchdayResult);


    }
}
