package com.cadri.goalguess.dto;

import lombok.Data;

@Data
public class MatchResultRequestDTO {
    private Long matchId;
    private Integer homeGoals;
    private Integer awayGoals;
    private String winner;
    private Boolean penalties;
}
