package com.cadri.goalguess.mapper;

import com.cadri.goalguess.model.Team;
import org.mapstruct.Mapper;

@Mapper()
public interface TeamNameMapper {
    Team map(String source);
}
