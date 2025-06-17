package org.bartoszwojcik.hydropol.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.mapper.OrderMapper;
import org.bartoszwojcik.hydropol.model.classes.Order;
import org.bartoszwojcik.hydropol.repository.order.OrderRepository;
import org.bartoszwojcik.hydropol.service.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private static final String STATUS_IN_PROGRESS = "In progress";
    private static final String ORDER_ID = "1";

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    @DisplayName("findAll_withValidPageable_returnsListOfOrderDto")
    void findAll_withValidPageable_returnsListOfOrderDto() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Order order = getOrder();
        OrderDto orderDto = getOrderDto();

        List<Order> orders = List.of(order);
        Page<Order> orderPage = new PageImpl<>(orders);

        when(orderRepository.findAll(pageable)).thenReturn(orderPage);
        when(orderMapper.toDto(order)).thenReturn(orderDto);

        // When
        List<OrderDto> result = orderService.findAll(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderDto, result.get(0));
        verify(orderRepository, times(1)).findAll(pageable);
        verify(orderMapper, times(1)).toDto(order);
        verifyNoMoreInteractions(orderRepository, orderMapper);
    }

    @Test
    @DisplayName("findInProgress_withValidPageable_returnsListOfOrderDto")
    void findInProgress_withValidPageable_returnsListOfOrderDto() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Order order = getOrder();
        OrderDto orderDto = getOrderDto();

        List<Order> orders = List.of(order);

        when(orderRepository.findByOrderStatus(STATUS_IN_PROGRESS, pageable)).thenReturn(orders);
        when(orderMapper.toDto(order)).thenReturn(orderDto);

        // When
        List<OrderDto> result = orderService.findInProgress(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderDto, result.get(0));
        verify(orderRepository, times(1)).findByOrderStatus(STATUS_IN_PROGRESS, pageable);
        verify(orderMapper, times(1)).toDto(order);
        verifyNoMoreInteractions(orderRepository, orderMapper);
    }

    private Order getOrder() {
        Order order = new Order();
        order.setId(ORDER_ID);
        return order;
    }

    private OrderDto getOrderDto() {
        OrderDto dto = new OrderDto();
        dto.setId(ORDER_ID);
        return dto;
    }
}
