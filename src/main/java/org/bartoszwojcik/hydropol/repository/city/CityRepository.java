package org.bartoszwojcik.hydropol.repository.city;

import java.util.Optional;
import org.bartoszwojcik.hydropol.model.classes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repozytorium JPA dla encji {@link City}.
 * Udostępnia podstawowe operacje CRUD oraz możliwość wykonywania zapytań
 * specyfikacyjnych (Specification API).
 */
public interface CityRepository extends JpaRepository<City, Long>,
        JpaSpecificationExecutor<City> {

    /**
     * Znajduje miasto po jego unikalnej nazwie.
     *
     * @param name nazwa miasta
     * @return opcjonalny obiekt {@link City} jeśli istnieje miasto o podanej nazwie
     */
    Optional<City> findByName(String name);

    /**
     * Aktualizuje nazwę miasta o podanym identyfikatorze.
     *
     * @param id identyfikator miasta
     * @param cityName nowa nazwa miasta
     * @return liczba zaktualizowanych rekordów (powinna być 0 lub 1)
     */
    @Modifying
    @Transactional
    @Query("UPDATE City c SET c.name = :cityName WHERE c.id = :id")
    int updateByCityName(Long id, String cityName);
}
