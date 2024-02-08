package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.LeagueDTO;
import com.cadri.goalguess.model.League;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper()
public interface LeaguesMapper {
    List<LeagueDTO> map(List<League> source);
}
