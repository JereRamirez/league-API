package com.jr.league.leagueapi.mapper;

import com.jr.league.leagueapi.dto.CoachDto;
import com.jr.league.leagueapi.model.Coach;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfig.class, uses = TeamMapper.class)
public interface CoachMapper {
    CoachDto coachDtoFromCoach(Coach coach);

    Coach coachFromCoachDto(CoachDto dto);
}
