package org.bartoszwojcik.hydropol.mapper;

import org.bartoszwojcik.hydropol.config.MapperConfig;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.model.classes.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {

    @Mapping(target = "postCode", source = "postCode")
    @Mapping(target = "houseNr", source = "houseNr")
    @Mapping(target = "assignedTo", source = "assignedTo.id")
    OrderDto toDto(Order order);

    @Mapping(target = "postCode", source = "postCode")
    @Mapping(target = "houseNr", source = "houseNr")
    @Mapping(target = "assignedTo", ignore = true)
    Order toEntity(OrderDto orderDto);
}

