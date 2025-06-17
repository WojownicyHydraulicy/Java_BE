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

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CitiesController {
    private final CityService cityService;

    // DELETE /cities/name/{cityName}
    @DeleteMapping("/name/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public Object deleteCity(@PathVariable String cityName) {
        return cityService.deleteCity(cityName);
    }

    // POST /cities/add
    @PostMapping("/add")
    public CityDto addCity(@RequestBody CityDto cityDto) {
        return cityService.addCity(cityDto);
    }

    // GET /cities/all
    @GetMapping("/all")
    public List<CityDto> findAll(Pageable pageable) {
        return cityService.findAll(pageable);
    }

}
