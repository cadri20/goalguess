package com.cadri.goalguess.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Data
public class MatchdayResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @Getter(AccessLevel.NONE)
    private Matchday matchday;

    @OneToMany()
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "matchday_result_id")
    private List<MatchResult> results;


}
