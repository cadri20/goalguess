package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchdayDTO;
import com.cadri.goalguess.model.Matchday;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MatchdayMapper extends Converter<Matchday, MatchdayDTO> {
    MatchdayDTO map(Matchday source);

}
