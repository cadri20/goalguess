package com.cadri.goalguess.dto;

import lombok.Data;

@Data
public class MatchResultDTO {
    private Long id;
    private int homeGoals;
    private int awayGoals;
    private boolean penalties;
    private TeamDTO winner;
}
