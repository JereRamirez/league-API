package com.jr.league.leagueapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * DTO for {@link com.jr.league.leagueapi.model.Coach}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CoachDto(String name, LocalDate dateOfBirth, String nationality) implements TeamMemberDto {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String getNationality() {
        return nationality;
    }
}