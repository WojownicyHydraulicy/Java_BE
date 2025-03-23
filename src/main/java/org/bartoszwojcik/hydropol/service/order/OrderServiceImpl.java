package org.bartoszwojcik.hydropol.service.order;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.mapper.OrderMapper;
import org.bartoszwojcik.hydropol.repository.order.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }
}
