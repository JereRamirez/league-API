package com.jr.league.leagueapi.repository;

import com.jr.league.leagueapi.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    boolean existsByCode(String code);
}