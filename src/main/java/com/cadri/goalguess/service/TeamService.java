package com.cadri.goalguess.service;

import com.cadri.goalguess.dto.TeamDTO;
import com.cadri.goalguess.exception.RestException;
import com.cadri.goalguess.model.League;
import com.cadri.goalguess.model.Team;
import com.cadri.goalguess.repository.LeagueRepository;
import com.cadri.goalguess.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private ConversionService conversionService;

    private LeagueRepository leagueRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);

    public TeamService(TeamRepository teamRepository, ConversionService conversionService, LeagueRepository leagueRepository) {
        this.teamRepository = teamRepository;
        this.conversionService = conversionService;
        this.leagueRepository = leagueRepository;
    }




}
