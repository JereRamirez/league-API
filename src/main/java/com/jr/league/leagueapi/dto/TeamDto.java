package com.jr.league.leagueapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link com.jr.league.leagueapi.model.Team}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TeamDto(String name, String tla, String shortName, String areaName, String address,
                      List<PlayerDto> squad, CoachDto coach, Set<CompetitionDto> competitions) implements Serializable {
}