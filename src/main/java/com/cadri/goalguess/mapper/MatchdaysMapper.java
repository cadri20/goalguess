package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.MatchdayDTO;
import com.cadri.goalguess.model.Matchday;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper()
public interface MatchdaysMapper {
    List<MatchdayDTO> map(List<Matchday> matchdays);
}
