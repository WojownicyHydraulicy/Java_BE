package org.bartoszwojcik.hydropol.mapper;

import org.bartoszwojcik.hydropol.config.MapperConfig;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.model.classes.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting between {@link Order} entity and {@link OrderDto}.
 */
@Mapper(config = MapperConfig.class)
public interface OrderMapper {

    /**
     * Converts an {@link Order} entity to an {@link OrderDto}.
     *
     * @param order the order entity to convert
     * @return the converted Order DTO
     */
    @Mapping(target = "postCode", source = "postCode")
    @Mapping(target = "houseNr", source = "houseNr")
    @Mapping(target = "assignedTo", source = "assignedTo.id")
    OrderDto toDto(Order order);

    /**
     * Converts an {@link OrderDto} to an {@link Order} entity.
     * The field 'assignedTo' is ignored during this mapping.
     *
     * @param orderDto the order DTO to convert
     * @return the converted Order entity
     */
    @Mapping(target = "postCode", source = "postCode")
    @Mapping(target = "houseNr", source = "houseNr")
    @Mapping(target = "assignedTo", ignore = true)
    Order toEntity(OrderDto orderDto);
}
