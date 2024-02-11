package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchdayRequestDTO;
import com.cadri.goalguess.model.Match;
import com.cadri.goalguess.model.Matchday;
import com.cadri.goalguess.model.Team;
import com.cadri.goalguess.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class MatchdayRequestDTOMapper implements Converter<MatchdayRequestDTO, Matchday> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchdayRequestDTOMapper.class);
    private TeamRepository teamRepository;

    public MatchdayRequestDTOMapper(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Matchday convert(MatchdayRequestDTO source) {
        LOGGER.debug("Converting MatchdayRequestDTO to Matchday: {}", source);
        Matchday matchday = new Matchday();
        matchday.setDate(source.getDate());
        matchday.setName(source.getName());
        matchday.setId(source.getId());


        MatchsRequestDTOMapper matchsRequestDTOMapper = new MatchsRequestDTOMapper(teamRepository);
        matchday.setMatches(matchsRequestDTOMapper.convert(source.getMatches()));

        return matchday;
    }
}
