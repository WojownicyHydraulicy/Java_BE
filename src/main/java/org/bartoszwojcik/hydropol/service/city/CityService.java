package org.bartoszwojcik.hydropol.service.city;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.city.CityDto;
import org.springframework.data.domain.Pageable;

public interface CityService {
    String deleteCity(String cityName);

    CityDto addCity(CityDto cityDto);

    List<CityDto> findAll(Pageable pageable);
}
