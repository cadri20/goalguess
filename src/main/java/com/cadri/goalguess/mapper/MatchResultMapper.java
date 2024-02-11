package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchResultDTO;
import com.cadri.goalguess.model.MatchResult;
import org.mapstruct.Mapper;

@Mapper()
public interface MatchResultMapper {
    MatchResultDTO map(MatchResult matchResult);
}
