package com.jr.league.leagueapi.service;

import com.jr.league.leagueapi.dto.CoachDto;
import com.jr.league.leagueapi.mapper.CoachMapper;
import com.jr.league.leagueapi.model.Coach;
import com.jr.league.leagueapi.repository.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoachService {
    private CoachRepository repository;
    private CoachMapper mapper;

    public List<CoachDto> getCoachesFrom(final String leagueCode, final String teamName) {
        final List<Coach> coaches = repository.findCoachesByCompetitionCodeAndTeamName(leagueCode, teamName);
        return coaches.stream()
                .map(mapper::coachDtoFromCoach)
                .collect(Collectors.toList());
    }

    public Coach getCoachFrom(final String teamName) {
        return repository.findByTeam_Name(teamName);
    }
}
