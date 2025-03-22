package org.bartoszwojcik.hydropol.service.city;

import org.bartoszwojcik.hydropol.dto.city.CityDto;

public interface CityService {
    String deleteCity(String cityName);

    CityDto addCity(CityDto cityDto);
}
