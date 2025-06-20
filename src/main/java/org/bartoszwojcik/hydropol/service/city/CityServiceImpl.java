package org.bartoszwojcik.hydropol.service.city;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.city.CityDto;
import org.bartoszwojcik.hydropol.mapper.CityMapper;
import org.bartoszwojcik.hydropol.repository.city.CityRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link CityService} that provides operations for managing cities.
 */
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    /**
     * Deletes a city by its name.
     *
     * @param cityName the name of the city to delete
     * @return confirmation message if deletion is successful
     * @throws EntityNotFoundException if no city with the given name exists
     */
    @Override
    public String deleteCity(String cityName) {
        cityRepository.delete(
                cityRepository.findByName(cityName).orElseThrow(
                        () -> new EntityNotFoundException("city with this name does not exist")
                )
        );
        return "City deleted";
    }

    /**
     * Adds a new city to the repository.
     *
     * @param cityDto DTO containing the city details to add
     * @return the added city as a DTO
     */
    @Override
    public CityDto addCity(CityDto cityDto) {
        return cityMapper.toDto(
                cityRepository.save(
                        cityMapper.toModel(cityDto)
                )
        );
    }

    /**
     * Finds all cities with pagination.
     *
     * @param pageable pagination information
     * @return list of cities as DTOs
     */
    @Override
    public List<CityDto> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable).stream()
                .map(cityMapper::toDto)
                .toList();
    }
}
