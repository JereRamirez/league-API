package com.jr.league.leagueapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO for {@link com.jr.league.leagueapi.model.Competition}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CompetitionDto(CompetitionDto.Area area, String name, String code) {
    public record Area(long id, String name, String code, String flag) {
    }
}
