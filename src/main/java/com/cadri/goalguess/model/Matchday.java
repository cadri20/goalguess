package com.cadri.goalguess.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Matchday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;

    @OneToMany
    @JoinColumn(name = "matchday_id")
    private List<Match> matches;

    @OneToMany
    @JoinColumn(name = "matchday_id")
    private List<Prediction> predictions;



}
