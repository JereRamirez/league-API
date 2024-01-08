package com.jr.league.leagueapi.service;

import com.jr.league.leagueapi.dto.CompetitionDto;
import com.jr.league.leagueapi.mapper.CompetitionMapper;
import com.jr.league.leagueapi.model.Competition;
import com.jr.league.leagueapi.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompetitionService {
    private CompetitionRepository repository;
    private CompetitionMapper mapper;

    public boolean existsByCode(final String leagueCode) {
        return repository.existsByCode(leagueCode);
    }

    public Competition save(final CompetitionDto competitionRequest) {
        return repository.save(mapper.competitionDtoToCompetition(competitionRequest));
    }

}
