package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.LeagueDTO;
import com.cadri.goalguess.model.League;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface LeagueDTOMapper extends Converter<LeagueDTO, League>{

    @Override
    League convert(LeagueDTO source);
}
