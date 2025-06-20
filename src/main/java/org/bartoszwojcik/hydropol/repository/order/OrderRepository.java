package org.bartoszwojcik.hydropol.repository.order;

import java.util.List;
import org.bartoszwojcik.hydropol.model.classes.Order;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repozytorium JPA dla encji {@link Order}.
 * Udostępnia metody do wyszukiwania zamówień oraz standardowe operacje CRUD.
 */
public interface OrderRepository extends JpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {

    /**
     * Znajduje listę zamówień o podanym statusie z paginacją.
     *
     * @param orderStatus status zamówienia (np. "IN_PROGRESS")
     * @param pageable parametry paginacji i sortowania
     * @return lista zamówień spełniających kryterium statusu
     */
    List<Order> findByOrderStatus(String orderStatus, Pageable pageable);

    /**
     * Znajduje listę zamówień przypisanych do użytkownika o podanym identyfikatorze.
     *
     * @param assignedTo identyfikator użytkownika, do którego przypisane są zamówienia
     * @return lista zamówień przypisanych do danego użytkownika
     */
    List<Order> findByAssignedToId(Long assignedTo);

    /**
     * Sprawdza, czy istnieje przynajmniej jedno zamówienie przypisane do danego użytkownika.
     *
     * @param assignedTo użytkownik, dla którego sprawdzane jest istnienie zamówienia
     * @return {@code true} jeśli istnieje zamówienie przypisane do użytkownika, {@code false} w przeciwnym wypadku
     */
    Boolean existsOrderByAssignedTo(User assignedTo);
}
