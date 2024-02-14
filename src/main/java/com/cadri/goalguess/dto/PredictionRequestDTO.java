package com.cadri.goalguess.dto;

import lombok.Data;

import java.util.List;

@Data
public class PredictionRequestDTO {
    private String predicter;
    private List<MatchResultRequestDTO> results;
}
