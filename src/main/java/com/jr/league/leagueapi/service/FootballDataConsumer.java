package com.jr.league.leagueapi.service;

import com.jr.league.leagueapi.dto.CompetitionDto;
import com.jr.league.leagueapi.dto.TeamDto;
import com.jr.league.leagueapi.dto.TeamResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FootballDataConsumer {
    private final String baseUrl = "https://api.football-data.org/v4/competitions/";

    private final String apiKey = "04d26e6c733645b78368cec82af042d1";

    private final RestTemplate restTemplate;
    private final HttpEntity<Void> requestEntity;

    public FootballDataConsumer() {
        this.restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        requestEntity = new HttpEntity<>(headers);
    }

    public CompetitionDto getCompetitionDetails(final String competitionCode) {
        final String apiUrl = baseUrl + competitionCode;

        final ResponseEntity<CompetitionDto> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, requestEntity, CompetitionDto.class);

        return responseEntity.getBody();
    }

    public List<TeamDto> getTeamsForCompetition(final String competitionCode) {
        final String apiUrl = baseUrl + competitionCode + "/teams";

        final ResponseEntity<TeamResponseDto> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, requestEntity, TeamResponseDto.class);


        return responseEntity.getBody().teams();
    }

}
