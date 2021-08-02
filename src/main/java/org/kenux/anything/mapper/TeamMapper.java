package org.kenux.anything.mapper;

import org.kenux.anything.domain.dto.TeamDto;
import org.kenux.anything.domain.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <pre>
 * 서비스 명   : anythingTest
 * 패키지 명   : org.kenux.anything.mapper
 * 클래스 명   : TeamMapper
 * 설명       :
 *
 * ====================================================================================
 *
 * </pre>
 **/
@Mapper
public interface TeamMapper {
    TeamMapper instance = Mappers.getMapper(TeamMapper.class);

    Team toTeamEntity(TeamDto teamDto);
    @Mapping(target = "id", ignore = true)
    TeamDto toTeamDto(Team team);
}
