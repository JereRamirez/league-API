package com.jr.league.leagueapi.controller;

import com.jr.league.leagueapi.dto.CoachDto;
import com.jr.league.leagueapi.dto.PlayerDto;
import com.jr.league.leagueapi.dto.TeamDto;
import com.jr.league.leagueapi.dto.TeamMemberDto;
import com.jr.league.leagueapi.exception.LeagueNotFoundException;
import com.jr.league.leagueapi.mapper.TeamMapper;
import com.jr.league.leagueapi.model.Player;
import com.jr.league.leagueapi.model.Team;
import com.jr.league.leagueapi.service.CoachService;
import com.jr.league.leagueapi.service.CompetitionService;
import com.jr.league.leagueapi.service.PlayerService;
import com.jr.league.leagueapi.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ApiController {
    private PlayerService playerService;
    private CompetitionService competitionService;
    private CoachService coachService;
    private TeamService teamService;
    private TeamMapper teamMapper;

    @GetMapping("/players/{leagueCode}")
    public ResponseEntity<List<TeamMemberDto>> getTeamMembersBy(@PathVariable("leagueCode") final String leagueCode,
                                                                @RequestParam(required = false) final String teamName) {
        if (!competitionService.existsByCode(leagueCode)) {
            throw new LeagueNotFoundException(leagueCode);
        }

        final List<PlayerDto> playerDtos = playerService.getPlayersFrom(leagueCode, teamName);
        final List<TeamMemberDto> teamMemberDtos = new ArrayList<>(playerDtos);

        if (playerDtos.isEmpty()) {
            final List<CoachDto> coaches = coachService.getCoachesFrom(leagueCode, teamName);
            teamMemberDtos.addAll(coaches);
        }

        return new ResponseEntity<>(teamMemberDtos, HttpStatus.OK);
    }

    @GetMapping("/teams/{teamName}")
    public ResponseEntity<TeamDto> getTeamBy(@PathVariable("teamName") final String teamName,
                                             @RequestParam(required = false) final boolean resolveMembers) {
        final Team team = teamService.getTeamByName(teamName);

        if (resolveMembers) {
            final List<Player> players = playerService.getPlayersFrom(teamName);
            team.setPlayers(players);
            if (players.isEmpty()) {
                team.setCoach(coachService.getCoachFrom(teamName));
            }
        }

        return new ResponseEntity<>(teamMapper.teamToTeamDto(team), HttpStatus.OK);
    }

}
