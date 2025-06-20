package org.bartoszwojcik.hydropol.dto.city;

import lombok.Data;

/**
 * Data Transfer Object representing a city.
 */
@Data
public class CityDto {

    /** The name of the city */
    private String name;

    /** The country where the city is located */
    private String country;

    /** The timezone of the city */
    private String timezone;
}
