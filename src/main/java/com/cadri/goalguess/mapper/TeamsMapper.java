package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.TeamDTO;
import com.cadri.goalguess.model.Team;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper()
public interface TeamsMapper {
    List<TeamDTO> map(List<Team> source);
}
