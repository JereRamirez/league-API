package com.jr.league.leagueapi.mapper;

import com.jr.league.leagueapi.dto.PlayerDto;
import com.jr.league.leagueapi.model.Player;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfig.class, uses = TeamMapper.class)
public interface PlayerMapper {

    PlayerDto playerDtoFromPlayer(Player player);

    Player playerFromPlayerDto(PlayerDto dto);

}
