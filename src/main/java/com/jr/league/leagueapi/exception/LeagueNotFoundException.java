package com.jr.league.leagueapi.exception;

public class LeagueNotFoundException extends RuntimeException {

    public LeagueNotFoundException(final String leagueCode) {
        super("League not found with code: " + leagueCode);
    }
}
