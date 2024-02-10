package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchRequestDTO;
import com.cadri.goalguess.exception.RestException;
import com.cadri.goalguess.model.Match;
import com.cadri.goalguess.model.Team;
import com.cadri.goalguess.repository.TeamRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class MatchRequestDTOMapper implements Converter<MatchRequestDTO, Match> {
    TeamRepository teamRepository;

    public MatchRequestDTOMapper(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    @Override
    public Match convert(MatchRequestDTO source) {
        Optional<Team> homeTeam = teamRepository.findByName(source.getHomeTeam());
        Optional<Team> awayTeam = teamRepository.findByName(source.getAwayTeam());
        if(homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Home or away team not valid");
        }

        Match match = new Match();
        match.setHomeTeam(homeTeam.get());
        match.setAwayTeam(awayTeam.get());

        return match;
    }
}
