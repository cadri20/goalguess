package com.cadri.goalguess.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class MatchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PositiveOrZero
    private int homeGoals;
    @NotNull
    @PositiveOrZero
    private int awayGoals;

    @NotNull
    private boolean penalties;

    @ManyToOne
    private Team winner;

    @ManyToOne
    @JoinColumn(name = "match_id")

    private Match match;




}
