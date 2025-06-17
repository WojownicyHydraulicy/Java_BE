package org.bartoszwojcik.hydropol.service.order;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    List<OrderDto> findAll(Pageable pageable);

    List<OrderDto> findInProgress(Pageable pageable);

}
