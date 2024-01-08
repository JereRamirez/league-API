package com.jr.league.leagueapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TeamResponseDto(List<TeamDto> teams) {
    @Override
    public List<TeamDto> teams() {
        return teams;
    }
}
