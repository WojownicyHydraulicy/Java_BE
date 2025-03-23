package org.bartoszwojcik.hydropol.repository.city;

import java.util.Optional;
import org.bartoszwojcik.hydropol.model.classes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CityRepository extends JpaRepository<City, Long>,
        JpaSpecificationExecutor<City> {
    Optional<City> findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE City c SET c.name = :cityName WHERE c.id = :id")
    int updateByCityName(Long id, String cityName);
}
