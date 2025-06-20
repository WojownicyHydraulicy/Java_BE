package org.bartoszwojcik.hydropol.repository.user;

import java.util.List;
import java.util.Optional;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repozytorium JPA dla encji {@link User}.
 * Udostępnia metody do wyszukiwania użytkowników oraz operacje aktualizacji danych użytkownika.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User> {

    /**
     * Znajduje użytkownika po adresie email, ładując jednocześnie powiązaną rolę (fetch join).
     *
     * @param email adres email użytkownika
     * @return optional użytkownika z podanym emailem
     */
    @EntityGraph(attributePaths = {"role"})
    Optional<User> findUserByEmail(String email);

    /**
     * Znajduje użytkownika po nazwie użytkownika (username).
     *
     * @param username nazwa użytkownika
     * @return optional użytkownika o podanym username
     */
    Optional<User> findUserByUsername(String username);

    /**
     * Znajduje listę użytkowników o podanym username z paginacją.
     *
     * @param username nazwa użytkownika do wyszukania
     * @param pageable parametry paginacji i sortowania
     * @return lista użytkowników pasujących do podanego username
     */
    List<User> findByUsername(String username, Pageable pageable);

    /**
     * Aktualizuje przypisanie miasta (city) dla użytkownika o podanym ID.
     *
     * @param id ID użytkownika, którego miasto ma zostać zmienione
     * @param cityId ID miasta, które ma zostać przypisane do użytkownika
     * @return liczba zaktualizowanych rekordów (powinna być 1, jeśli użytkownik istnieje)
     */
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.citiesId.id = :cityId WHERE u.id = :id")
    int updateCity(Long id, Long cityId);
}
