package org.bartoszwojcik.hydropol.service.order;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.springframework.data.domain.Pageable;

/**
 * @brief Service interface for managing orders.
 *
 * Provides methods to retrieve all orders and orders that are in progress.
 */
public interface OrderService {

    /**
     * @brief Retrieves a paginated list of all orders.
     *
     * @param pageable Pagination information.
     * @return List of OrderDto objects representing all orders.
     */
    List<OrderDto> findAll(Pageable pageable);

    /**
     * @brief Retrieves a paginated list of orders that are currently in progress.
     *
     * @param pageable Pagination information.
     * @return List of OrderDto objects representing orders in progress.
     */
    List<OrderDto> findInProgress(Pageable pageable);

}
