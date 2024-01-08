package com.jr.league.leagueapi.service;

import com.jr.league.leagueapi.dto.PlayerDto;
import com.jr.league.leagueapi.mapper.PlayerMapper;
import com.jr.league.leagueapi.model.Player;
import com.jr.league.leagueapi.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService {
    private PlayerRepository repository;
    private PlayerMapper mapper;

    public List<PlayerDto> getPlayersFrom(final String leagueCode, final String teamName) {
        final List<Player> playersByCompetitionCode = repository.findPlayersByCompetitionCodeAndTeamName(leagueCode, teamName);
        return playersByCompetitionCode.stream()
                .map(mapper::playerDtoFromPlayer)
                .collect(Collectors.toList());
    }


    public List<Player> getPlayersFrom(final String teamName) {
        return repository.findByTeam_Name(teamName);
    }
}
