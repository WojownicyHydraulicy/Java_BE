package org.bartoszwojcik.hydropol.service.city;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.city.CityDto;
import org.bartoszwojcik.hydropol.mapper.CityMapper;
import org.bartoszwojcik.hydropol.repository.city.CityRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public String deleteCity(String cityName) {
        cityRepository.delete(
                cityRepository.findByName(cityName).orElseThrow(
                        () -> new EntityNotFoundException("city with this name does not exist")
                )
        );
        return "City deleted";
    }

    @Override
    public CityDto addCity(CityDto cityDto) {
        return cityMapper.toDto(
                cityRepository.save(
                       cityMapper.toModel(cityDto)
                )
        );
    }

    @Override
    public List<CityDto> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable).stream()
                .map(cityMapper::toDto)
                .toList();
    }

}
