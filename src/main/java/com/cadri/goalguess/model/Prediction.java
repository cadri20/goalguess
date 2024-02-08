package com.cadri.goalguess.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String predicter;
    @OneToMany
    @JoinColumn(name = "prediction_id")
    private List<MatchResult> results;
}
