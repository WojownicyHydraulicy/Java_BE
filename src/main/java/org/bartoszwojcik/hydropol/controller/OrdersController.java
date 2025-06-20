package org.bartoszwojcik.hydropol.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.service.order.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing orders.
 * <p>
 * Provides endpoints to retrieve all orders and orders that are currently in progress.
 * </p>
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrdersController {

    /** Service handling order-related operations */
    private final OrderService orderService;

    /**
     * Retrieves a paginated list of all orders.
     *
     * @param pageable pagination information
     * @return list of all orders as DTOs
     */
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    /**
     * Retrieves a paginated list of orders that are currently in progress.
     *
     * @param pageable pagination information
     * @return list of in-progress orders as DTOs
     */
    @GetMapping("/all/progress")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getInProgressOrders(Pageable pageable) {
        return orderService.findInProgress(pageable);
    }
}
