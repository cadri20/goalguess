package com.cadri.goalguess.dto;

import lombok.Data;

import java.util.List;

@Data
public class PredictionDTO {
    private Long id;
    private String predicter;
    private List<MatchResultDTO> results;
}
