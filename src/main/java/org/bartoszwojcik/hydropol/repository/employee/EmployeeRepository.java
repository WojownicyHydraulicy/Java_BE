package org.bartoszwojcik.hydropol.repository.employee;

import java.util.Optional;
import org.bartoszwojcik.hydropol.model.classes.Employee;
import org.bartoszwojcik.hydropol.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repozytorium JPA dla encji {@link Employee}.
 * Umożliwia wykonywanie operacji CRUD oraz zapytań specyfikacyjnych na pracownikach.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {

    /**
     * Znajduje pracownika na podstawie identyfikatora powiązanego użytkownika.
     *
     * @param userId identyfikator użytkownika
     * @return opcjonalny obiekt {@link Employee}, jeśli istnieje pracownik powiązany z danym użytkownikiem
     */
    Optional<Employee> findByUserId(Long userId);

    /**
     * Aktualizuje rolę pracownika (workerRole) na podstawie identyfikatora użytkownika.
     *
     * @param id identyfikator użytkownika powiązanego z pracownikiem
     * @param role nowa rola pracownika {@link UserRole}
     * @return liczba zaktualizowanych rekordów (powinna być 0 lub 1)
     */
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.workerRole = :role WHERE e.user.id = :id")
    int updateByUserId(Long id, UserRole role);
}
