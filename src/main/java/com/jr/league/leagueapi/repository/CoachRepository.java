package com.jr.league.leagueapi.repository;

import com.jr.league.leagueapi.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach, Long> {

    @Query("select c from Coach c where c.team.name = ?1")
    Coach findByTeam_Name(String name);

    @Query("""
            select c from Coach c inner join c.team.competitions competitions
            where (:name is null or c.team.name = :name ) and competitions.code = :code""")
    List<Coach> findCoachesByCompetitionCodeAndTeamName(@Param("name") String teamName, @Param("code") String leagueCode);

}