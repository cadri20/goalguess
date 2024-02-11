package com.cadri.goalguess.dto;

import lombok.Data;

@Data
public class MatchResultDTO {
    private Long id;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private boolean penalties;
    private TeamDTO winner;
}
