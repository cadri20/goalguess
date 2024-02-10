package com.cadri.goalguess.dto;

import com.cadri.goalguess.model.Team;
import lombok.Data;

@Data
public class MatchDTO {
    Long id;
    Team homeTeam;
    Team awayTeam;
}
