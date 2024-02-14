package com.cadri.goalguess.dto;

import com.cadri.goalguess.model.Team;
import lombok.Data;

@Data
public class MatchDTO {
    private Long id;
    private Team homeTeam;
    private Team awayTeam;
}
