package com.cadri.goalguess.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
    @Cascade(CascadeType.ALL)
    private List<MatchResult> results;

    @ManyToOne
    @JoinColumn(name = "matchday_id")
    @JsonIgnore
    @ToString.Exclude
    private Matchday matchday;

}
