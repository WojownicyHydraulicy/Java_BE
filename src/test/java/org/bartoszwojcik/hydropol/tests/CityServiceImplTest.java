package org.bartoszwojcik.hydropol.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.bartoszwojcik.hydropol.dto.city.CityDto;
import org.bartoszwojcik.hydropol.mapper.CityMapper;
import org.bartoszwojcik.hydropol.model.classes.City;
import org.bartoszwojcik.hydropol.repository.city.CityRepository;
import org.bartoszwojcik.hydropol.service.city.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock private CityRepository cityRepository;
    @Mock private CityMapper cityMapper;

    @InjectMocks private CityServiceImpl cityService;

    private CityDto cityDto;
    private City city;

    @BeforeEach
    void setUp() {
        cityDto = new CityDto();
        cityDto.setName("Warsaw");

        city = new City();
        city.setName("Warsaw");
    }

    @Test
    void addCity_savesAndReturnsDto() {
        when(cityMapper.toModel(cityDto)).thenReturn(city);
        when(cityRepository.save(city)).thenReturn(city);
        when(cityMapper.toDto(city)).thenReturn(cityDto);

        CityDto result = cityService.addCity(cityDto);

        assertEquals(cityDto, result);
        verify(cityMapper).toModel(cityDto);
        verify(cityRepository).save(city);
        verify(cityMapper).toDto(city);
    }

    @Test
    void deleteCity_deletesCityIfFound() {
        when(cityRepository.findByName("Warsaw")).thenReturn(Optional.of(city));

        String result = cityService.deleteCity("Warsaw");

        assertEquals("City deleted", result);
        verify(cityRepository).delete(city);
    }

    @Test
    void deleteCity_throwsIfCityNotFound() {
        when(cityRepository.findByName("UnknownCity")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> cityService.deleteCity("UnknownCity"));
    }

    @Test
    void findAll_returnsListOfCityDto() {
        Pageable pageable = PageRequest.of(0, 10);
        when(cityRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(city)));
        when(cityMapper.toDto(city)).thenReturn(cityDto);

        List<CityDto> result = cityService.findAll(pageable);

        assertEquals(1, result.size());
        assertEquals("Warsaw", result.get(0).getName());
        verify(cityMapper).toDto(city);
    }
}
