package com.cadri.goalguess.service;

import com.cadri.goalguess.dto.MatchdayDTO;
import com.cadri.goalguess.exception.RestException;
import com.cadri.goalguess.model.Matchday;
import com.cadri.goalguess.repository.MatchdayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class MatchdayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchdayService.class);
    private MatchdayRepository matchdayRepository;

    private ConversionService conversionService;

    public MatchdayService(MatchdayRepository matchdayRepository, ConversionService conversionService) {
        this.matchdayRepository = matchdayRepository;
        this.conversionService = conversionService;
    }

    public MatchdayDTO findNextMatchday(Long leagueId){
        Matchday matchday = matchdayRepository.findTopByLeagueIdOrderByDateDesc(leagueId).orElse(null);
        if(matchday == null){
            LOGGER.debug("Not exist matchday for the league with the id {}", leagueId);
            throw new RestException(HttpStatus.NOT_FOUND, "Matchday not found");
        }
        return conversionService.convert(matchday, MatchdayDTO.class);
    }
}
