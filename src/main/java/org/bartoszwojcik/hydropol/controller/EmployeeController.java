package org.bartoszwojcik.hydropol.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.bartoszwojcik.hydropol.dto.order.OrderDto;
import org.bartoszwojcik.hydropol.service.employee.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing employee-related endpoints.
 * <p>
 * Provides endpoints for retrieving all employees and orders associated with a specific employee.
 * </p>
 */
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {

    /** Service handling employee business logic */
    private final EmployeeService employeeService;

    /**
     * Retrieves a paginated list of all employees.
     *
     * @param pageable pagination information
     * @return list of employees as DTOs
     */
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAllEmployees(Pageable pageable) {
        return employeeService.findAll(pageable);
    }

    /**
     * Retrieves a paginated list of orders for a specific employee identified by username.
     *
     * @param username the username of the employee
     * @param pageable pagination information
     * @return list of orders associated with the employee
     */
    @GetMapping("/all/{username}/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getEmployeeOrders(@PathVariable String username, Pageable pageable) {
        return employeeService.findEmployeeOrders(username, pageable);
    }
}
