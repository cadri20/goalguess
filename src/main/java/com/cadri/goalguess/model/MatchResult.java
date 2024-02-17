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


    //return a value between 0 and 1 depending on how close the prediction is to the actual result
    public double compare(MatchResult prediction){
        double points = 0;
        if(this.homeGoals == prediction.homeGoals){
            points += 0.25;
        }

        if(this.awayGoals == prediction.awayGoals){
            points += 0.25;
        }

        if(this.penalties == prediction.penalties){
            points += 0.25;
        }

        if(this.winner.equals(prediction.winner)){
            points += 0.25;
        }

        return points;

    }
}
