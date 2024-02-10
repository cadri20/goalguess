package com.cadri.goalguess.dto;

import com.cadri.goalguess.model.Prediction;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MatchdayRequestDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private List<MatchRequestDTO> matches;
    private List<Prediction> predictions;
}
