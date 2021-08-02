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
@Mapper(componentModel = "spring")
public interface TeamMapper extends GenericMapper<TeamDto, Team> {
}
