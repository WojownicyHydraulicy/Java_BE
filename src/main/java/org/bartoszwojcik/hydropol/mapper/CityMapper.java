package org.bartoszwojcik.hydropol.mapper;

import org.bartoszwojcik.hydropol.config.MapperConfig;
import org.bartoszwojcik.hydropol.dto.city.CityDto;
import org.bartoszwojcik.hydropol.model.classes.City;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CityMapper {

    City toModel(CityDto cityDto);

    CityDto toDto(City city);
}
