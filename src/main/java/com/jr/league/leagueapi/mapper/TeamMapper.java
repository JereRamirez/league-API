package com.jr.league.leagueapi.mapper;

import com.jr.league.leagueapi.dto.TeamDto;
import com.jr.league.leagueapi.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class, uses = {PlayerMapper.class, CoachMapper.class, CompetitionMapper.class})
public interface TeamMapper {

    @Mapping(target = "squad", source = "players")
    TeamDto teamToTeamDto(Team team);

    @Mapping(target = "players", source = "squad")
    @Mapping(target = "competitions", ignore = true)
    @Mapping(target = "id", ignore = true)
    Team teamDtoToTeam(TeamDto teamDto);

}
