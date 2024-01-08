package com.jr.league.leagueapi.exception;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(final String teamName) {
        super("Team not found with name: " + teamName);
    }
}
