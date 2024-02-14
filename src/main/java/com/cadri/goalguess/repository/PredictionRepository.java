package com.cadri.goalguess.repository;

import com.cadri.goalguess.model.Matchday;
import com.cadri.goalguess.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {

}
