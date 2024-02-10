package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cadri.goalguess.exception.RestException;
import com.cadri.goalguess.model.Match;
import com.cadri.goalguess.model.Team;
import com.cadri.goalguess.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

public class MatchsRequestDTOMapper implements Converter<List<MatchRequestDTO>, List<Match>> {
    private static Logger LOGGER = LoggerFactory.getLogger(MatchsRequestDTOMapper.class);
    private TeamRepository teamRepository;

    public MatchsRequestDTOMapper(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    
    @Override
    public List<Match> convert(List<MatchRequestDTO> source) {
        LOGGER.debug("Converting MatchRequestDTO to Match: {}", source);
        List<Match> matches = new ArrayList<>();
        for (MatchRequestDTO matchRequestDTO : source) {
            Match match = new Match();
            Optional<Team> homeTeam = teamRepository.findByName(matchRequestDTO.getHomeTeam());
            Optional<Team> awayTeam = teamRepository.findByName(matchRequestDTO.getAwayTeam());
            if(homeTeam.isEmpty() || awayTeam.isEmpty()) {
                throw new RestException(HttpStatus.BAD_REQUEST, "Home or away team not valid");
            }
            match.setHomeTeam(homeTeam.get());
            match.setAwayTeam(awayTeam.get());
            matches.add(match);
        }

        return matches;
    }

}
