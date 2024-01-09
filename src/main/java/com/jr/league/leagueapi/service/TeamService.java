package com.jr.league.leagueapi.service;

import com.jr.league.leagueapi.dto.TeamDto;
import com.jr.league.leagueapi.exception.TeamNotFoundException;
import com.jr.league.leagueapi.mapper.TeamMapper;
import com.jr.league.leagueapi.model.Coach;
import com.jr.league.leagueapi.model.Competition;
import com.jr.league.leagueapi.model.Team;
import com.jr.league.leagueapi.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {
    private TeamRepository repository;
    private TeamMapper mapper;

    public Team getTeamByName(final String teamName) {
        return repository.findByName(teamName)
                .orElseThrow(() -> new TeamNotFoundException(teamName));
    }

    public List<Team> bulkInsert(final List<TeamDto> teamDtos, final Competition competition) {
        final List<Team> teams = teamDtos.stream()
                .map(mapper::teamDtoToTeam)
                .map(team -> updateOrCreateTeam(team, competition))
                .collect(Collectors.toList());

        return repository.saveAll(teams);
    }

    private Team updateOrCreateTeam(final Team team, final Competition competition) {
        final Optional<Team> teamByName = repository.findByName(team.getName());

        return teamByName.map(persistedTeam -> {
            persistedTeam.addCompetition(competition);
            return persistedTeam;
        }).orElseGet(() -> {
            team.getPlayers().forEach(player -> {
                        if (player.getNationality() == null) {
                            player.setNationality(team.getName());
                        }
                        player.setTeam(team);
                    }
            );
            final Coach coach = team.getCoach();
            if (!Objects.isNull(coach)) {
                coach.setTeam(team);
            }
            team.addCompetition(competition);
            return team;
        });
    }

}
