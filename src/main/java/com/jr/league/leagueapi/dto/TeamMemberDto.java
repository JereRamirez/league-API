package com.jr.league.leagueapi.dto;

import java.io.Serializable;
import java.time.LocalDate;

public interface TeamMemberDto extends Serializable {
    String getName();

    LocalDate getDateOfBirth();

    String getNationality();
}
