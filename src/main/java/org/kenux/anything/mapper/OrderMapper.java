package org.kenux.anything.mapper;

import org.kenux.anything.web.dto.OrderDto;
import org.kenux.anything.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingInheritanceStrategy;

@Mapper(componentModel = "spring",
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG,
        uses = {BaseTimeMapper.class})
public interface OrderMapper extends GenericMapper<OrderDto, Order> {
}
