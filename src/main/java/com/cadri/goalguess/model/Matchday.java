package com.cadri.goalguess.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Matchday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String name;

    @FutureOrPresent
    @NotBlank
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @Getter(AccessLevel.NONE)
    @JoinColumn(name = "league_id")
    private League league;


    @OneToMany
    @JoinColumn(name = "matchday_id")
    @Cascade(CascadeType.ALL)
    private List<Match> matches;

    @OneToMany
    @JoinColumn(name = "matchday_id")
    private List<Prediction> predictions;



}
