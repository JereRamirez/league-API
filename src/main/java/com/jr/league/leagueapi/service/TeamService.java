package com.jr.league.leagueapi.service;

import com.jr.league.leagueapi.dto.TeamDto;
import com.jr.league.leagueapi.exception.TeamNotFoundException;
import com.jr.league.leagueapi.mapper.TeamMapper;
import com.jr.league.leagueapi.model.Competition;
import com.jr.league.leagueapi.model.Player;
import com.jr.league.leagueapi.model.Team;
import com.jr.league.leagueapi.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .collect(Collectors.toList());

        for (final Team team : teams) {
            for (final Player player : team.getPlayers()) {
                player.setTeam(team);
            }
            team.getCoach().setTeam(team);
            team.addCompetition(competition);
        }

        return repository.saveAll(teams);
    }

}
