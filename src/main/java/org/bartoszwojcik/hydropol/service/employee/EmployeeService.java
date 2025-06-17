package org.bartoszwojcik.hydropol.service.employee;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    List<EmployeeDto> findAll(Pageable pageable);

    List<OrderDto> findEmployeeOrders(String email, Pageable pageable);

    EmployeeDto workerUnavailability(String email);

    EmployeeDto workerAvailability(String email, Integer availability);
}
