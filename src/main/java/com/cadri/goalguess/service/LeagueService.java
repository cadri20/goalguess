package com.cadri.goalguess.service;

import com.cadri.goalguess.dto.*;
import com.cadri.goalguess.exception.RestException;
import com.cadri.goalguess.model.League;
import com.cadri.goalguess.model.Match;
import com.cadri.goalguess.model.Matchday;
import com.cadri.goalguess.model.Team;
import com.cadri.goalguess.repository.LeagueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {
    private LeagueRepository leagueRepository;
    private ConversionService conversionService;

    private TeamService teamService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LeagueService.class);

    @Autowired
    public LeagueService(LeagueRepository leagueRepository, ConversionService conversionService, TeamService teamService) {
        this.leagueRepository = leagueRepository;
        this.conversionService = conversionService;
        this.teamService = teamService;
    }

    public List<LeagueDTO> findAll(){
        List<League> leagues = leagueRepository.findAll();
        LOGGER.debug("Leagues found: {}", leagues);
        return conversionService.convert(leagues, List.class);
    }

    public LeagueDTO findById(Long id){
        Optional<League> league = leagueRepository.findById(id);
        if(league.isEmpty()){
            LOGGER.debug("Not exist league with the id {}", id);
            throw new RestException(HttpStatus.NOT_FOUND, "League not found");
        }
        return conversionService.convert(league.get(), LeagueDTO.class);
    }

    public LeagueDTO save(LeagueDTO league){
        var transformed = conversionService.convert(league, League.class);
        LOGGER.debug("League to save: {}", transformed);
        return conversionService.convert(leagueRepository.save(transformed), LeagueDTO.class);
    }

    public List<TeamDTO> getTeams(Long leagueId){
        Optional<League> league = leagueRepository.findById(leagueId);
        if(league.isEmpty()){
            LOGGER.debug("Not exist league with the id {}", leagueId);
            throw new RuntimeException("League not found");
        }
        return conversionService.convert(league.get().getTeams(), List.class);
    }

    public TeamDTO createTeam(Long leagueId, TeamDTO team){
        Optional<League> league = leagueRepository.findById(leagueId);
        if(league.isEmpty()){
            LOGGER.debug("Not exist league with the id {}", leagueId);
            throw new RestException(HttpStatus.NOT_FOUND, "League not found");
        }
        var transformed = conversionService.convert(team, Team.class);
        League leagueToSave = league.get();
        leagueToSave.getTeams().add(transformed);
        leagueRepository.save(leagueToSave);
        Team teamCreated = leagueToSave.getTeams().get(leagueToSave.getTeams().size() - 1);
        return conversionService.convert(teamCreated, TeamDTO.class);
    }

    public List<MatchdayDTO> getMatchdays(Long leagueId){
        Optional<League> league = leagueRepository.findById(leagueId);
        if(league.isEmpty()){
            LOGGER.debug("Not exist league with the id {}", leagueId);
            throw new RestException(HttpStatus.NOT_FOUND, "League not found");
        }
        return conversionService.convert(league.get().getMatchdays(), List.class);
    }

    public MatchdayDTO createMatchday(Long leagueId, MatchdayRequestDTO matchday){
        Optional<League> league = leagueRepository.findById(leagueId);
        if(league.isEmpty()){
            LOGGER.debug("Not exist league with the id {}", leagueId);
            throw new RestException(HttpStatus.NOT_FOUND, "League not found");
        }
        Matchday matchdayToSave = conversionService.convert(matchday, Matchday.class);
        LOGGER.debug("Matchday to save: {}", matchdayToSave);
        League leagueToSave = league.get();
        leagueToSave.getMatchdays().add(matchdayToSave);
        LOGGER.debug("League to save: {}", leagueToSave);
        League leagueCreated = leagueRepository.save(leagueToSave);
        Matchday matchdayCreated = leagueCreated.getMatchdays().get(leagueToSave.getMatchdays().size() - 1);
        LOGGER.debug("Matchday created: {}", matchdayCreated);
        return conversionService.convert(matchdayCreated, MatchdayDTO.class);
    }






}
