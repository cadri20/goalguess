package com.cadri.goalguess.service;

import com.cadri.goalguess.dto.LeagueDTO;
import com.cadri.goalguess.dto.TeamDTO;
import com.cadri.goalguess.model.League;
import com.cadri.goalguess.model.Team;
import com.cadri.goalguess.repository.LeagueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {
    private LeagueRepository leagueRepository;
    private ConversionService conversionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LeagueService.class);

    @Autowired
    public LeagueService(LeagueRepository leagueRepository, ConversionService conversionService) {
        this.leagueRepository = leagueRepository;
        this.conversionService = conversionService;
    }

    public List<LeagueDTO> findAll(){
        List<League> leagues = leagueRepository.findAll();
        LOGGER.debug("Leagues found: {}", leagues);
        return conversionService.convert(leagues, List.class);
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
            throw new RuntimeException("League not found");
        }
        var transformed = conversionService.convert(team, Team.class);
        League leagueToSave = league.get();
        leagueToSave.getTeams().add(transformed);
        leagueRepository.save(leagueToSave);
        Team teamCreated = leagueToSave.getTeams().get(leagueToSave.getTeams().size() - 1);
        return conversionService.convert(teamCreated, TeamDTO.class);
    }
}
