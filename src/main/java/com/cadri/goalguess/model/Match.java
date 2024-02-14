package com.cadri.goalguess.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Getter(AccessLevel.NONE)
    private MatchResult result;



    public Match() {
    }
}
