package com.cadri.goalguess.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "soccer_match")
@Data
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;

    @OneToOne
    private MatchResult result;
}
