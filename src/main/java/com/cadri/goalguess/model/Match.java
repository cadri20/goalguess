package com.cadri.goalguess.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "soccer_match")
@Data
@AllArgsConstructor
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

    public Match() {
    }
}
