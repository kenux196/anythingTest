package org.kenux.anything.mapper;

import org.kenux.anything.web.dto.BaseTimeDto;
import org.kenux.anything.domain.entity.BaseTimeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingInheritanceStrategy;

@Mapper(componentModel = "spring",
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface BaseTimeMapper extends GenericMapper<BaseTimeDto, BaseTimeEntity> {
}
