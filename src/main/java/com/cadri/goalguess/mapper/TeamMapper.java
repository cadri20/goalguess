package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.TeamDTO;
import com.cadri.goalguess.model.Team;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface TeamMapper extends Converter<Team, TeamDTO> {
    @Override
    TeamDTO convert(Team source);
}
