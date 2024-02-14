package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.PredictionRequestDTO;
import com.cadri.goalguess.model.Prediction;
import com.cadri.goalguess.repository.MatchRepository;
import com.cadri.goalguess.repository.TeamRepository;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class PredictionRequestDTOMapper implements Converter<PredictionRequestDTO, Prediction> {
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public PredictionRequestDTOMapper(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public Prediction convert(PredictionRequestDTO predictionRequestDTO) {
        Prediction prediction = new Prediction();
        prediction.setPredicter(predictionRequestDTO.getPredicter());
        prediction.setResults(predictionRequestDTO.getResults().stream().map(matchResultRequestDTO -> new MatchResultRequestDTOMapper(teamRepository, matchRepository).convert(matchResultRequestDTO)).collect(Collectors.toList()));
        return prediction;
    }
}
