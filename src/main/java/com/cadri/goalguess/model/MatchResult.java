package com.cadri.goalguess.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class MatchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int homeTeamGoals;
    private int awayTeamGoals;
    private boolean penalties;

    @ManyToOne
    private Team winner;




}
