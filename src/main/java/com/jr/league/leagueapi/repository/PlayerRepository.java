package com.jr.league.leagueapi.repository;

import com.jr.league.leagueapi.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("select p from Player p where p.team.name = ?1")
    List<Player> findByTeam_Name(String name);

    @Query("""
                select p from Player p
                inner join p.team.competitions competitions
                where (competitions.code like concat('%', :code, '%'))
                  and (:name is null or p.team.name = :name)
            """)
    List<Player> findPlayersByCompetitionCodeAndTeamName(String code, String name);

}