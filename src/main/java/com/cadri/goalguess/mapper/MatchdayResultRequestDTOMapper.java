package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchdayResultRequestDTO;
import com.cadri.goalguess.model.MatchdayResult;
import com.cadri.goalguess.repository.MatchRepository;
import com.cadri.goalguess.repository.TeamRepository;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class MatchdayResultRequestDTOMapper implements Converter<MatchdayResultRequestDTO, MatchdayResult> {
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public MatchdayResultRequestDTOMapper(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }
    @Override
    public MatchdayResult convert(MatchdayResultRequestDTO source) {
        MatchdayResult matchdayResult = new MatchdayResult();
        matchdayResult.setId(source.getId());
        matchdayResult.setResults(source.getResults().stream().map(matchResultRequestDTO -> new MatchResultRequestDTOMapper(teamRepository, matchRepository).convert(matchResultRequestDTO)).collect(Collectors.toList()));
        return matchdayResult;
    }
}
