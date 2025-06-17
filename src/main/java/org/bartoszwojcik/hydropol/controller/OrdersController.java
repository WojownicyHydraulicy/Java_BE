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

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrdersController {
    private final OrderService orderService;

    // GET /orders/all
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    // GET /orders/all
    @GetMapping("/all/progress")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getInProgressOrders(Pageable pageable) {
        return orderService.findInProgress(pageable);
    }
}
