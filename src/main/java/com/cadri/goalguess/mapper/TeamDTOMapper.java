package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.TeamDTO;
import com.cadri.goalguess.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface TeamDTOMapper extends Converter<TeamDTO, Team>{
    @Override
    Team convert(TeamDTO source);



}
