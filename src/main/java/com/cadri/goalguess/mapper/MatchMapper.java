package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchDTO;
import com.cadri.goalguess.model.Match;
import org.mapstruct.Mapper;

@Mapper
public interface MatchMapper  {
    MatchDTO map(Match source);
}
