package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchdayDTO;
import com.cadri.goalguess.model.Matchday;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MatchdayDTOMapper extends Converter<MatchdayDTO, Matchday> {
    Matchday map(MatchdayDTO source);
}
