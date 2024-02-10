package com.cadri.goalguess.repository;

import com.cadri.goalguess.model.Matchday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchdayRepository extends JpaRepository<Matchday, Long> {
    Optional<Matchday> findTopByLeagueIdOrderByDateDesc(Long leagueId);
}
