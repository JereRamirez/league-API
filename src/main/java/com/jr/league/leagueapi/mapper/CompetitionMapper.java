package com.jr.league.leagueapi.mapper;

import com.jr.league.leagueapi.dto.CompetitionDto;
import com.jr.league.leagueapi.model.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface CompetitionMapper {
    CompetitionDto competitionToCompetitionDto(Competition competition);

    @Mapping(target = "areaName", source = "area.name")
    Competition competitionDtoToCompetition(CompetitionDto dto);
}
