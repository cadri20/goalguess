package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchResultDTO;
import com.cadri.goalguess.model.MatchResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper()
public interface MatchResultsMapper {
    List<MatchResultDTO> map(List<MatchResult> matchResults);
}
