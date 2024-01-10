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

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class FootballDataConsumer {
    private final String baseUrl = "https://api.football-data.org/v4/competitions/";

    private final String apiKey = "04d26e6c733645b78368cec82af042d1";

    private final RestTemplate restTemplate;
    private final HttpEntity<Void> requestEntity;
    private final int maxRequestsPerMinute = 10;
    private final BlockingQueue<Instant> requestQueue = new ArrayBlockingQueue<>(maxRequestsPerMinute);


    public FootballDataConsumer() {
        this.restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        requestEntity = new HttpEntity<>(headers);
    }

    public CompetitionDto getCompetitionDetails(final String competitionCode) {
        enforceRateLimit();
        final String apiUrl = baseUrl + competitionCode;

        final ResponseEntity<CompetitionDto> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, requestEntity, CompetitionDto.class);

        return responseEntity.getBody();
    }

    public List<TeamDto> getTeamsForCompetition(final String competitionCode) {
        enforceRateLimit();
        final String apiUrl = baseUrl + competitionCode + "/teams";

        final ResponseEntity<TeamResponseDto> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, requestEntity, TeamResponseDto.class);


        return responseEntity.getBody().teams();
    }

    private void enforceRateLimit() {
        final Instant now = Instant.now();
        requestQueue.removeIf(token -> Duration.between(token, now).toMinutes() >= 1);

        while (requestQueue.size() >= maxRequestsPerMinute) {
            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        requestQueue.offer(now);
    }

}
