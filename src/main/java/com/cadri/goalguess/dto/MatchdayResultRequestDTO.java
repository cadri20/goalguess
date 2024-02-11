package com.cadri.goalguess.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchdayResultRequestDTO {
    private Long id;
    private List<MatchResultRequestDTO> results;
}
