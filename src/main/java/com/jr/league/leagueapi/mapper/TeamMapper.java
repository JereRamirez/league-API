package com.jr.league.leagueapi.mapper;

import com.jr.league.leagueapi.dto.CoachDto;
import com.jr.league.leagueapi.dto.TeamDto;
import com.jr.league.leagueapi.model.Coach;
import com.jr.league.leagueapi.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = CommonMapperConfig.class, uses = {PlayerMapper.class, CoachMapper.class, CompetitionMapper.class})
public interface TeamMapper {

    @Mapping(target = "squad", source = "players")
    TeamDto teamToTeamDto(Team team);

    @Mapping(target = "players", source = "squad")
    @Mapping(target = "competitions", ignore = true)
    @Mapping(target = "coach", source = "coach", qualifiedByName = "mapCoachDtoToCoach")
    @Mapping(target = "id", ignore = true)
    Team teamDtoToTeam(TeamDto teamDto);

    @Named("mapCoachDtoToCoach")
    static Coach mapCoachDtoToCoach(final CoachDto coachDto) {
        if (coachDto.name() != null) {
            return CoachMapper.INSTANCE.coachFromCoachDto(coachDto);
        }
        return null;
    }

}
