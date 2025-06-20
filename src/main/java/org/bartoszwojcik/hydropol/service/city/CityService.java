package org.bartoszwojcik.hydropol.service.city;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.city.CityDto;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing City entities.
 */
public interface CityService {

    /**
     * Deletes a city by its name.
     *
     * @param cityName the name of the city to delete
     * @return a message indicating the result of the deletion operation
     */
    String deleteCity(String cityName);

    /**
     * Adds a new city to the system.
     *
     * @param cityDto the data transfer object representing the city to add
     * @return the added city as a CityDto
     */
    CityDto addCity(CityDto cityDto);

    /**
     * Retrieves all cities with pagination support.
     *
     * @param pageable pagination information
     * @return a list of CityDto objects representing the cities
     */
    List<CityDto> findAll(Pageable pageable);
}
