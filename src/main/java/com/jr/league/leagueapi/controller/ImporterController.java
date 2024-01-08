package com.jr.league.leagueapi.controller;

import com.jr.league.leagueapi.dto.CompetitionDto;
import com.jr.league.leagueapi.dto.TeamDto;
import com.jr.league.leagueapi.model.Competition;
import com.jr.league.leagueapi.service.CompetitionService;
import com.jr.league.leagueapi.service.FootballDataConsumer;
import com.jr.league.leagueapi.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/importer")
@AllArgsConstructor
public class ImporterController {
    private FootballDataConsumer footballDataConsumer;
    private CompetitionService competitionService;
    private TeamService teamService;

    @GetMapping("/{leagueCode}")
    public void importLeague(@PathVariable final String leagueCode) {
        final CompetitionDto competitionDto = footballDataConsumer.getCompetitionDetails(leagueCode);
        final Competition competition = competitionService.save(competitionDto);
        final List<TeamDto> teamDtos = footballDataConsumer.getTeamsForCompetition(leagueCode);
        teamService.bulkInsert(teamDtos, competition);
    }
}
