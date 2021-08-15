package org.kenux.anything.mapper;

import org.kenux.anything.domain.dto.TeamDto;
import org.kenux.anything.domain.entity.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper extends GenericMapper<TeamDto, Team> {
}
