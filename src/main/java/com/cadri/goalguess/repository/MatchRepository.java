package com.cadri.goalguess.repository;

import com.cadri.goalguess.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long>{

}
