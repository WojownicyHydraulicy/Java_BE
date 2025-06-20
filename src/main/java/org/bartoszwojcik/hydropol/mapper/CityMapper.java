package org.bartoszwojcik.hydropol.mapper;

import org.bartoszwojcik.hydropol.config.MapperConfig;
import org.bartoszwojcik.hydropol.dto.city.CityDto;
import org.bartoszwojcik.hydropol.model.classes.City;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link City} entity and {@link CityDto}.
 */
@Mapper(config = MapperConfig.class)
public interface CityMapper {

    /**
     * Converts a {@link CityDto} to a {@link City} entity.
     *
     * @param cityDto the city DTO to convert
     * @return the converted City entity
     */
    City toModel(CityDto cityDto);

    /**
     * Converts a {@link City} entity to a {@link CityDto}.
     *
     * @param city the city entity to convert
     * @return the converted City DTO
     */
    CityDto toDto(City city);
}
