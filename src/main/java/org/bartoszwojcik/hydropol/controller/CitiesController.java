package org.bartoszwojcik.hydropol.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.city.CityDto;
import org.bartoszwojcik.hydropol.service.city.CityService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing city entities.
 * <p>
 * Provides endpoints to add, delete, and retrieve cities with pagination support.
 * </p>
 */
@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CitiesController {

    /** Service layer for city-related operations */
    private final CityService cityService;

    /**
     * Deletes a city by its name.
     *
     * @param cityName the name of the city to delete
     * @return an object representing the result of the deletion operation
     */
    @DeleteMapping("/name/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public Object deleteCity(@PathVariable String cityName) {
        return cityService.deleteCity(cityName);
    }

    /**
     * Adds a new city.
     *
     * @param cityDto the data transfer object containing city details
     * @return the added city as a DTO
     */
    @PostMapping("/add")
    public CityDto addCity(@RequestBody CityDto cityDto) {
        return cityService.addCity(cityDto);
    }

    /**
     * Retrieves a paginated list of all cities.
     *
     * @param pageable pagination information
     * @return list of city DTOs according to the pagination settings
     */
    @GetMapping("/all")
    public List<CityDto> findAll(Pageable pageable) {
        return cityService.findAll(pageable);
    }
}
