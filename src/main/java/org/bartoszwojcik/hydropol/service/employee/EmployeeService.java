package org.bartoszwojcik.hydropol.service.employee;

import java.util.List;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing employee-related operations.
 */
public interface EmployeeService {

    /**
     * Retrieves a paginated list of all employees.
     *
     * @param pageable pagination information
     * @return list of employees as DTOs
     */
    List<EmployeeDto> findAll(Pageable pageable);

    /**
     * Finds orders assigned to an employee identified by their email.
     *
     * @param email employee's email
     * @param pageable pagination information
     * @return list of order DTOs assigned to the employee
     */
    List<OrderDto> findEmployeeOrders(String email, Pageable pageable);

    /**
     * Sets the employee's availability status to unavailable.
     *
     * @param email employee's email
     * @return updated employee DTO reflecting unavailability
     */
    EmployeeDto workerUnavailability(String email);

    /**
     * Updates the employee's availability with the given availability value.
     *
     * @param email employee's email
     * @param availability new availability value
     * @return updated employee DTO reflecting the new availability
     */
    EmployeeDto workerAvailability(String email, Integer availability);
}
