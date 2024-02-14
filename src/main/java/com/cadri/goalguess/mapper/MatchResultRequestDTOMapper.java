package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchResultRequestDTO;
import com.cadri.goalguess.exception.RestException;
import com.cadri.goalguess.model.Match;
import com.cadri.goalguess.model.MatchResult;
import com.cadri.goalguess.model.Team;
import com.cadri.goalguess.repository.MatchRepository;
import com.cadri.goalguess.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MatchResultRequestDTOMapper implements Converter<MatchResultRequestDTO, MatchResult> {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public MatchResultRequestDTOMapper(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public MatchResult convert(MatchResultRequestDTO source) {
        MatchResult matchResult = new MatchResult();
        matchResult.setHomeGoals(source.getHomeGoals());
        matchResult.setAwayGoals(source.getAwayGoals());
        Optional<Team> winner = teamRepository.findByName(source.getWinner());
        if(winner.isEmpty()) {
            throw new RestException(HttpStatus.NOT_FOUND, "Winner team not found");
        }else{
            matchResult.setWinner(winner.get());
        }
        Optional<Match> match = matchRepository.findById(source.getMatchId());
        if(match.isEmpty()) {
            throw new RestException(HttpStatus.NOT_FOUND, "Match not found");
        }else{
            matchResult.setMatch(match.get());
        }
        return matchResult;
    }
}
