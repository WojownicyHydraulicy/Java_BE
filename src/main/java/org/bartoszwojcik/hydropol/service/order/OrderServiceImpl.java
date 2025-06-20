package org.bartoszwojcik.hydropol.service.order;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.mapper.OrderMapper;
import org.bartoszwojcik.hydropol.repository.order.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @brief Implementation of the OrderService interface.
 *
 * Provides operations related to order retrieval.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    /**
     * @brief Retrieves a paginated list of all orders.
     *
     * @param pageable Pageable object to specify pagination parameters.
     * @return List of OrderDto representing all orders.
     */
    @Override
    public List<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    /**
     * @brief Retrieves a paginated list of orders with status "In progress".
     *
     * @param pageable Pageable object to specify pagination parameters.
     * @return List of OrderDto representing orders that are in progress.
     */
    @Override
    public List<OrderDto> findInProgress(Pageable pageable) {
        return orderRepository.findByOrderStatus("In progress", pageable)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

}
